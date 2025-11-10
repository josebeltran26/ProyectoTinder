/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.EstudianteDAO;
import com.mycompany.DAO.IEstudianteDAO;
import com.mycompany.entities.Carrera;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */
public class EstudianteService implements IEstudianteService {

    private IEstudianteDAO estudianteDAO = new EstudianteDAO();

    @Override
    public void crearEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante.getNombre() == null || estudiante.getNombre().isEmpty()) {
            throw new Exception("Nombre obligatorio");
        }
        if (estudiante.getEdad() <= 0) {
            throw new Exception("Edad debe ser mayor a 0");
        }
        if (estudiante.getGenero() == null || estudiante.getGenero().isEmpty()) {
            throw new Exception("Género obligatorio");
        }
        if (estudiante.getCarrera() == null) {
            throw new Exception("Carrera obligatoria");
        }
        estudianteDAO.crear(estudiante);
    }

    @Override
    public Estudiante buscarEstudiantePorId(Long id) {
        return estudianteDAO.buscarPorId(id);
    }

    @Override
    public List<Estudiante> listarEstudiantes(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return estudianteDAO.listar(limit);
    }

    @Override
    public void actualizarEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante.getNombre() == null || estudiante.getNombre().isEmpty()) {
            throw new Exception("Nombre obligatorio");
        }
        if (estudiante.getEdad() <= 0) {
            throw new Exception("Edad debe ser mayor a 0");
        }
        estudianteDAO.actualizar(estudiante);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        estudianteDAO.eliminar(id);
    }

    @Override
    public List<Estudiante> buscarPorCarrera(Carrera carrera) {
        return estudianteDAO.buscarPorCarrera(carrera);
    }

    @Override
    public List<Estudiante> buscarPorEdadRango(int minEdad, int maxEdad) {
        return estudianteDAO.buscarPorEdadRango(minEdad, maxEdad);
    }

    @Override
    public List<Estudiante> buscarPorGenero(String genero) {
        return estudianteDAO.buscarPorGenero(genero);
    }
}
