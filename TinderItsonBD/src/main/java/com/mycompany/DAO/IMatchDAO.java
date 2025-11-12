/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Match;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IMatchDAO {

    void crear(Match match);

    Match buscarPorId(Long id);

    List<Match> listar(int limit);

    void actualizar(Match match);

    void eliminar(Long id);

    List<Match> buscarMatchesPorEstudiante(Estudiante estudiante);

    boolean existeMatch(Estudiante estudiante1, Estudiante estudiante2);

    void eliminarMatchPorEstudiantes(Estudiante e1, Estudiante e2);
}
