/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tinderitsonbd;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Josel
 */
public class TinderItsonBD {

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("TinderItsonPU");
        EntityManager em=emf.createEntityManager();
        //mysql.railway.internal
    }
}
