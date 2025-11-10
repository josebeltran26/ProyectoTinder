/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.CorreoDAO;
import com.mycompany.DAO.ICorreoDAO;
import com.mycompany.entities.Correo;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */


public class CorreoService implements ICorreoService {
    private ICorreoDAO correoDAO = new CorreoDAO();

    @Override
    public void crearCorreo(Correo correo) throws Exception {
      if (correo.getCorreo() == null || correo.getCorreo().isEmpty()) {
          throw new Exception("Correo obligatorio");
      }
      if (correoDAO.existeCorreo(correo.getCorreo())) {
          throw new Exception("Correo ya existe");
      }
      correoDAO.crear(correo);
  }

    @Override
    public Correo buscarCorreoPorId(Long id) {
        return correoDAO.buscarPorId(id);
    }

    @Override
    public List<Correo> listarCorreos(int limit) {
        if (limit > 100) limit = 100;
        return correoDAO.listar(limit);
    }

    @Override
    public void actualizarCorreo(Correo correo) {
        correoDAO.actualizar(correo);
    }

    @Override
    public void eliminarCorreo(Long id) {
        correoDAO.eliminar(id);
    }

    @Override
    public List<Correo> buscarPorEstudiante(Estudiante estudiante) {
        return correoDAO.buscarPorEstudiante(estudiante);
    }
}