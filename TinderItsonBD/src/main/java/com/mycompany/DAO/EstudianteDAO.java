/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Carrera;
import com.mycompany.entities.Estudiante;
import com.mycompany.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author Josel
 */
public class EstudianteDAO implements IEstudianteDAO {

    @Override
    public void crear(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(estudiante);
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
    public Estudiante buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Estudiante> listar(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(estudiante);
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
            Estudiante estudiante = em.find(Estudiante.class, id);
            if (estudiante != null) {
                em.remove(estudiante);
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
    public List<Estudiante> buscarPorCarrera(Carrera carrera) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.carrera = :carrera", Estudiante.class);
            query.setParameter("carrera", carrera);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Estudiante> buscarPorEdadRango(int minEdad, int maxEdad) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.edad BETWEEN :min AND :max", Estudiante.class);
            query.setParameter("min", minEdad);
            query.setParameter("max", maxEdad);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Estudiante> buscarPorGenero(String genero) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class);
            query.setParameter("genero", genero);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Estudiante buscarPorCredenciales(String correo, String contrasena) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery(
                    "SELECT e FROM Estudiante e JOIN e.correos c WHERE c.correo = :correo AND e.contrasena = :contrasena", Estudiante.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Estudiante> buscarPerfilesDisponibles(Long estudianteId, int limit) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Estudiante> query = em.createQuery(
                    "SELECT e FROM Estudiante e "
                    + "WHERE e.id != :estudianteId "
                    + "AND e.id NOT IN ("
                    + "SELECT l.receptor.id FROM Like l WHERE l.emisor.id = :estudianteId"
                    + ") "
                    + "AND e.id NOT IN ("
                    + "  SELECT m.estudiante1.id FROM Match m WHERE m.estudiante2.id = :estudianteId"
                    + ") "
                    + "AND e.id NOT IN ("
                    + "  SELECT m.estudiante2.id FROM Match m WHERE m.estudiante1.id = :estudianteId"
                    + ")", Estudiante.class);
            query.setParameter("estudianteId", estudianteId);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
