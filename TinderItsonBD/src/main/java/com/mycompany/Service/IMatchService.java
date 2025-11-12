/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Match;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IMatchService {

    void crearMatch(Match match) throws Exception;

    Match buscarMatchPorId(Long id);

    List<Match> listarMatches(int limit);

    void actualizarMatch(Match match);

    void eliminarMatch(Long id);

    List<Match> buscarMatchesPorEstudiante(Estudiante estudiante);

    void eliminarMatchEntreEstudiantes(Estudiante e1, Estudiante e2);
}
