/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Correo;
import com.mycompany.entities.Estudiante;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */

public class CorreoDAO implements ICorreoDAO {

    @Override
    public void crear(Correo correo) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(correo);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Correo buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Correo.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Correo> listar(int limit) {
        if (limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Correo> query = em.createQuery("SELECT c FROM Correo c", Correo.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Correo correo) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(correo);
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
            Correo correo = em.find(Correo.class, id);
            if (correo != null) {
                em.remove(correo);
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
    public List<Correo> buscarPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Correo> query = em.createQuery("SELECT c FROM Correo c WHERE c.estudiante = :estudiante", Correo.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeCorreo(String correo) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Correo c WHERE c.correo = :correo", Long.class);
            query.setParameter("correo", correo);
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }
}
