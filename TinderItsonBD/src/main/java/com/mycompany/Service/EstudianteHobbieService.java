/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.EstudianteHobbieDAO;
import com.mycompany.DAO.IEstudianteHobbieDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.EstudianteHobbie;
import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public class EstudianteHobbieService implements IEstudianteHobbieService {

    private IEstudianteHobbieDAO estudianteHobbieDAO = new EstudianteHobbieDAO();

    @Override
    public void crearEstudianteHobbie(EstudianteHobbie estudianteHobbie) throws Exception {
        if (estudianteHobbie.getEstudiante() == null || estudianteHobbie.getHobbie() == null) {
            throw new Exception("Estudiante y Hobbie obligatorios");
        }
        if (estudianteHobbieDAO.existeRelacion(estudianteHobbie.getEstudiante(), estudianteHobbie.getHobbie())) {
            throw new Exception("Relación ya existe");
        }
        estudianteHobbieDAO.crear(estudianteHobbie);
    }

    @Override
    public EstudianteHobbie buscarEstudianteHobbiePorId(Long id) {
        return estudianteHobbieDAO.buscarPorId(id);
    }

    @Override
    public List<EstudianteHobbie> listarEstudianteHobbies(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return estudianteHobbieDAO.listar(limit);
    }

    @Override
    public void actualizarEstudianteHobbie(EstudianteHobbie estudianteHobbie) {
        estudianteHobbieDAO.actualizar(estudianteHobbie);
    }

    @Override
    public void eliminarEstudianteHobbie(Long id) {
        estudianteHobbieDAO.eliminar(id);
    }

    @Override
    public List<EstudianteHobbie> buscarPorEstudiante(Estudiante estudiante) {
        return estudianteHobbieDAO.buscarPorEstudiante(estudiante);
    }

    @Override
    public List<EstudianteHobbie> buscarPorHobbie(Hobbie hobbie) {
        return estudianteHobbieDAO.buscarPorHobbie(hobbie);
    }

    @Override
    public boolean existeRelacion(Estudiante estudiante, Hobbie hobbie) {
        return estudianteHobbieDAO.existeRelacion(estudiante, hobbie);
    }
}
