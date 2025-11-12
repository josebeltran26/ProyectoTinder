/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import com.mycompany.entities.Estudiante;

public class SessionManager {

    private static Estudiante estudianteLogueado;

    private SessionManager() {
    }

    public static Estudiante getEstudianteLogueado() {
        return estudianteLogueado;
    }

    public static void setEstudianteLogueado(Estudiante estudiante) {
        SessionManager.estudianteLogueado = estudiante;
    }

    public static void cerrarSesion() {
        SessionManager.estudianteLogueado = null;
    }

    public static boolean isSesionIniciada() {
        return estudianteLogueado != null;
    }
}
