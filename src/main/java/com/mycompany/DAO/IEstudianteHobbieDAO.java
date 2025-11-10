/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.EstudianteHobbie;
import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IEstudianteHobbieDAO {

    void crear(EstudianteHobbie estudianteHobbie);

    EstudianteHobbie buscarPorId(Long id);

    List<EstudianteHobbie> listar(int limit);

    void actualizar(EstudianteHobbie estudianteHobbie);

    void eliminar(Long id);

    List<EstudianteHobbie> buscarPorEstudiante(Estudiante estudiante);

    List<EstudianteHobbie> buscarPorHobbie(Hobbie hobbie);

    boolean existeRelacion(Estudiante estudiante, Hobbie hobbie);
}
