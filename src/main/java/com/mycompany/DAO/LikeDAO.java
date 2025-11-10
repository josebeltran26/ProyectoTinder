/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */


public class LikeDAO implements ILikeDAO {

    @Override
    public void crear(Like like) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(like);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Like buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Like.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Like> listar(int limit) {
        if (limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Like> query = em.createQuery("SELECT l FROM Like l", Like.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Like like) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(like);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Like like = em.find(Like.class, id);
            if (like != null) {
                em.remove(like);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Like> buscarLikesDadosPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Like> query = em.createQuery("SELECT l FROM Like l WHERE l.likeD = :estudiante", Like.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Like> buscarLikesRecibidosPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Like> query = em.createQuery("SELECT l FROM Like l WHERE l.likeR = :estudiante", Like.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeLikeMutuo(Estudiante estudiante1, Estudiante estudiante2) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(l) FROM Like l WHERE (l.likeD = :e1 AND l.likeR = :e2) OR (l.likeD = :e2 AND l.likeR = :e1)", Long.class);
            query.setParameter("e1", estudiante1);
            query.setParameter("e2", estudiante2);
            return query.getSingleResult() >= 2;
        } finally {
            em.close();
        }
    }
}