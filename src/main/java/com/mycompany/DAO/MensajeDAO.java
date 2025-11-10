/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Mensaje;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */

public class MensajeDAO implements IMensajeDAO {

    @Override
    public void crear(Mensaje mensaje) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(mensaje);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Mensaje buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Mensaje.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Mensaje> listar(int limit) {
        if (limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Mensaje> query = em.createQuery("SELECT m FROM Mensaje m", Mensaje.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Mensaje mensaje) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(mensaje);
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
            Mensaje mensaje = em.find(Mensaje.class, id);
            if (mensaje != null) {
                em.remove(mensaje);
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
    public List<Mensaje> buscarMensajesPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Mensaje> query = em.createQuery("SELECT m FROM Mensaje m WHERE m.mensaje = :estudiante", Mensaje.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Mensaje> buscarMensajesEntreEstudiantes(Estudiante estudiante1, Estudiante estudiante2) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Mensaje> query = em.createQuery(
                "SELECT m FROM Mensaje m WHERE (m.mensaje = :e1 OR m.mensaje = :e2) AND m.contenido IS NOT NULL", Mensaje.class);
            query.setParameter("e1", estudiante1);
            query.setParameter("e2", estudiante2);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}