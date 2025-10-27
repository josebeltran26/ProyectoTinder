/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.ITelefonoDAO;
import com.mycompany.DAO.TelefonoDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Telefono;
import java.util.List;

/**
 *
 * @author Josel
 */
  
  public class TelefonoService implements ITelefonoService {
      private ITelefonoDAO telefonoDAO = new TelefonoDAO();

      @Override
      public void crearTelefono(Telefono telefono) throws Exception {
          if (telefono.getNumero() == null || telefono.getNumero().isEmpty()) {
              throw new Exception("Número obligatorio");
          }
          if (telefonoDAO.existeTelefono(telefono.getNumero())) {
              throw new Exception("Teléfono ya existe");
          }
          telefonoDAO.crear(telefono);
      }

      @Override
      public Telefono buscarTelefonoPorId(Long id) {
          return telefonoDAO.buscarPorId(id);
      }

      @Override
      public List<Telefono> listarTelefonos(int limit) {
          if (limit > 100) limit = 100;
          return telefonoDAO.listar(limit);
      }

      @Override
      public void actualizarTelefono(Telefono telefono) {
          telefonoDAO.actualizar(telefono);
      }

      @Override
      public void eliminarTelefono(Long id) {
          telefonoDAO.eliminar(id);
      }

      @Override
      public List<Telefono> buscarPorEstudiante(Estudiante estudiante) {
          return telefonoDAO.buscarPorEstudiante(estudiante);
      }
  }
  
