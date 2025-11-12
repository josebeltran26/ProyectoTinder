/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.IMatchDAO;
import com.mycompany.DAO.MatchDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Match;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Josel
 */
public class MatchService implements IMatchService {

    private IMatchDAO matchDAO = new MatchDAO();

    @Override
    public void crearMatch(Match match) throws Exception {
        if (match.getEstudiante1() == null || match.getEstudiante2() == null) {
            throw new Exception("Estudiantes obligatorios");
        }

        if (match.getFechaHora() == null) {
            // Se asume que el cambio de Calendar a LocalDateTime se ha aplicado
            match.setFechaHora(LocalDateTime.now());
        }

        matchDAO.crear(match);
    }

    @Override
    public Match buscarMatchPorId(Long id) {
        return matchDAO.buscarPorId(id);
    }

    @Override
    public List<Match> listarMatches(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return matchDAO.listar(limit);
    }

    @Override
    public void actualizarMatch(Match match) {
        matchDAO.actualizar(match);
    }

    @Override
    public void eliminarMatch(Long id) {
        matchDAO.eliminar(id);
    }

    @Override
    public List<Match> buscarMatchesPorEstudiante(Estudiante estudiante) {
        return matchDAO.buscarMatchesPorEstudiante(estudiante);
    }

    @Override
    public void eliminarMatchEntreEstudiantes(Estudiante e1, Estudiante e2) {
        matchDAO.eliminarMatchPorEstudiantes(e1, e2);
    }

    @Override
    public boolean existeMatch(Estudiante estudiante1, Estudiante estudiante2) {
        return matchDAO.existeMatch(estudiante1, estudiante2);
    }
}
