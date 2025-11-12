/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Carrera;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IEstudianteService {

    void crearEstudiante(Estudiante estudiante) throws Exception;

    Estudiante buscarEstudiantePorId(Long id);

    List<Estudiante> listarEstudiantes(int limit);

    void actualizarEstudiante(Estudiante estudiante) throws Exception;

    void eliminarEstudiante(Long id);

    List<Estudiante> buscarPorCarrera(Carrera carrera);

    List<Estudiante> buscarPorEdadRango(int minEdad, int maxEdad);

    List<Estudiante> buscarPorGenero(String genero);

    Estudiante autenticarEstudiante(String correo, String contrasena);

    List<Estudiante> buscarPerfilesParaSwipe(Long estudianteId, int limit);
}
