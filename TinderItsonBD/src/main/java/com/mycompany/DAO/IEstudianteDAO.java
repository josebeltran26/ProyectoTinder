/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Carrera;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IEstudianteDAO {

    void crear(Estudiante estudiante);

    Estudiante buscarPorId(Long id);

    List<Estudiante> listar(int limit);

    void actualizar(Estudiante estudiante);

    void eliminar(Long id);

    List<Estudiante> buscarPorCarrera(Carrera carrera);

    List<Estudiante> buscarPorEdadRango(int minEdad, int maxEdad);

    List<Estudiante> buscarPorGenero(String genero);

    Estudiante buscarPorCredenciales(String correo, String contrasena);

    List<Estudiante> buscarPerfilesDisponibles(Long estudianteId, int limit);
}
