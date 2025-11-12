/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Match;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */
public class MatchDAO implements IMatchDAO {

    @Override
    public void crear(Match match) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(match);
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
    public Match buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Match.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Match> listar(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m", Match.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Match match) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(match);
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
    public void eliminar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Match match = em.find(Match.class, id);
            if (match != null) {
                em.remove(match);
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
    public List<Match> buscarMatchesPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Match> query = em.createQuery(
                    "SELECT m FROM Match m WHERE m.estudiante1 = :estudiante OR m.estudiante2 = :estudiante", Match.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeMatch(Estudiante estudiante1, Estudiante estudiante2) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(m) FROM Match m WHERE (m.estudiante1 = :e1 AND m.estudiante2 = :e2) OR (m.estudiante1 = :e2 AND m.estudiante2 = :e1)", Long.class);
            query.setParameter("e1", estudiante1);
            query.setParameter("e2", estudiante2);
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminarMatchPorEstudiantes(Estudiante e1, Estudiante e2) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Match> query = em.createQuery(
                    "SELECT m FROM Match m WHERE (m.estudiante1 = :e1 AND m.estudiante2 = :e2) OR (m.estudiante1 = :e2 AND m.estudiante2 = :e1)", Match.class);
            query.setParameter("e1", e1);
            query.setParameter("e2", e2);

            List<Match> matches = query.getResultList();

            for (Match match : matches) {
                em.remove(match);
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

}
