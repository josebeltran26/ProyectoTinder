/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.EstudianteHobbie;
import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IEstudianteHobbieService {

    void crearEstudianteHobbie(EstudianteHobbie estudianteHobbie) throws Exception;

    EstudianteHobbie buscarEstudianteHobbiePorId(Long id);

    List<EstudianteHobbie> listarEstudianteHobbies(int limit);

    void actualizarEstudianteHobbie(EstudianteHobbie estudianteHobbie);

    void eliminarEstudianteHobbie(Long id);

    List<EstudianteHobbie> buscarPorEstudiante(Estudiante estudiante);

    List<EstudianteHobbie> buscarPorHobbie(Hobbie hobbie);
}
