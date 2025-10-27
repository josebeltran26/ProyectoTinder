/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.EstudianteHobbie;
import com.mycompany.entities.Hobbie;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */

public class EstudianteHobbieDAO implements IEstudianteHobbieDAO {

    @Override
    public void crear(EstudianteHobbie estudianteHobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(estudianteHobbie);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public EstudianteHobbie buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(EstudianteHobbie.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<EstudianteHobbie> listar(int limit) {
        if (limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<EstudianteHobbie> query = em.createQuery("SELECT eh FROM EstudianteHobbie eh", EstudianteHobbie.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(EstudianteHobbie estudianteHobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(estudianteHobbie);
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
            EstudianteHobbie estudianteHobbie = em.find(EstudianteHobbie.class, id);
            if (estudianteHobbie != null) {
                em.remove(estudianteHobbie);
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
    public List<EstudianteHobbie> buscarPorEstudiante(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<EstudianteHobbie> query = em.createQuery("SELECT eh FROM EstudianteHobbie eh WHERE eh.estudiante = :estudiante", EstudianteHobbie.class);
            query.setParameter("estudiante", estudiante);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<EstudianteHobbie> buscarPorHobbie(Hobbie hobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<EstudianteHobbie> query = em.createQuery("SELECT eh FROM EstudianteHobbie eh WHERE eh.hobbie = :hobbie", EstudianteHobbie.class);
            query.setParameter("hobbie", hobbie);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeRelacion(Estudiante estudiante, Hobbie hobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(eh) FROM EstudianteHobbie eh WHERE eh.estudiante = :estudiante AND eh.hobbie = :hobbie", Long.class);
            query.setParameter("estudiante", estudiante);
            query.setParameter("hobbie", hobbie);
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }
}
