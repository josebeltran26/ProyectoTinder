/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tinderitsonbd;

import Pantallas.InicioDao;
import com.mycompany.util.JpaUtil;

/**
 *
 * @author Josel
 */
public class TinderItsonBD {

    public static void main(String[] args) {
        try {
            javax.swing.SwingUtilities.invokeLater(() -> {
                InicioDao dialog = new InicioDao(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            });
            
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
