/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DAO;

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

public class HobbieDAO implements IHobbieDAO {

    @Override
    public void crear(Hobbie hobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hobbie);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobbie buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Hobbie.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hobbie> listar(int limit) {
        if (limit > 100) limit = 100;
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Hobbie> query = em.createQuery("SELECT h FROM Hobbie h", Hobbie.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Hobbie hobbie) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(hobbie);
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
            Hobbie hobbie = em.find(Hobbie.class, id);
            if (hobbie != null) {
                em.remove(hobbie);
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
    public List<Hobbie> buscarPorNombre(String nombre) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Hobbie> query = em.createQuery("SELECT h FROM Hobbie h WHERE h.nombre LIKE :nombre", Hobbie.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}