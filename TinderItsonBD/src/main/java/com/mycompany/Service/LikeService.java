/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.ILikeDAO;
import com.mycompany.DAO.IMatchDAO;
import com.mycompany.DAO.LikeDAO;
import com.mycompany.DAO.MatchDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import com.mycompany.entities.Match;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Josel
 */
public class LikeService implements ILikeService {

    private LikeDAO likeDAO = new LikeDAO();
    private MatchDAO matchDAO = new MatchDAO();

    @Override
    public void crearLike(Like like) throws Exception {
        if (like.getEmisor() == null || like.getReceptor() == null) {
            throw new Exception("Estudiantes obligatorios");
        }

        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            likeDAO.crearConEm(like, em);

            boolean esMatchMutuo = likeDAO.existeLikeMutuoConEm(like.getEmisor(), like.getReceptor(), em);

            if (esMatchMutuo && !matchDAO.existeMatch(like.getEmisor(), like.getReceptor())) {
                Match match = new Match();
                match.setEstudiante1(like.getEmisor());
                match.setEstudiante2(like.getReceptor());
                match.setFechaHora(LocalDateTime.now());
                matchDAO.crearConEm(match, em);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Like buscarLikePorId(Long id) {
        return likeDAO.buscarPorId(id);
    }

    @Override
    public List<Like> listarLikes(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return likeDAO.listar(limit);
    }

    @Override
    public void actualizarLike(Like like) {
        likeDAO.actualizar(like);
    }

    @Override
    public void eliminarLike(Long id) {
        likeDAO.eliminar(id);
    }

    @Override
    public List<Like> buscarLikesDadosPorEstudiante(Estudiante estudiante) {
        return likeDAO.buscarLikesDadosPorEstudiante(estudiante);
    }

    @Override
    public List<Like> buscarLikesRecibidosPorEstudiante(Estudiante estudiante) {
        return likeDAO.buscarLikesRecibidosPorEstudiante(estudiante);
    }

    @Override
    public Like buscarLikeDado(Estudiante emisor, Estudiante receptor) {
        return likeDAO.buscarLikePorEmisorYReceptor(emisor, receptor);
    }

    @Override
    public void eliminarLikeDado(Estudiante emisor, Estudiante receptor) throws Exception {
        if (emisor == null || receptor == null) {
            throw new Exception("Emisor y receptor son obligatorios.");
        }

        boolean existiaMatch = matchDAO.existeMatch(emisor, receptor);

        likeDAO.eliminarLikePorEmisorYReceptor(emisor, receptor);

        if (existiaMatch) {
            matchDAO.eliminarMatchPorEstudiantes(emisor, receptor);
        }
    }
}
