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
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Josel
 */
public class LikeService implements ILikeService {

    private ILikeDAO likeDAO = new LikeDAO();
    private IMatchDAO matchDAO = new MatchDAO();

    @Override
    public void crearLike(Like like) throws Exception {
        if (like.getEmisor() == null || like.getReceptor() == null) {
            throw new Exception("Estudiantes obligatorios");
        }

        likeDAO.crear(like);

        if (likeDAO.existeLikeMutuo(like.getEmisor(), like.getReceptor()) && !matchDAO.existeMatch(like.getEmisor(), like.getReceptor())) {
            Match match = new Match();
            match.setEstudiante1(like.getEmisor());
            match.setEstudiante2(like.getReceptor());
            match.setFechaHora(LocalDateTime.now());
            matchDAO.crear(match);
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
