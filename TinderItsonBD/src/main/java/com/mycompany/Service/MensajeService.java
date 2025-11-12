/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.IMatchDAO;
import com.mycompany.DAO.IMensajeDAO;
import com.mycompany.DAO.MatchDAO;
import com.mycompany.DAO.MensajeDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Mensaje;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Josel
 */


public class MensajeService implements IMensajeService {
    private IMensajeDAO mensajeDAO = new MensajeDAO();
    private IMatchDAO matchDAO = new MatchDAO();

    @Override
    public void crearMensaje(Mensaje mensaje) throws Exception {
        if (mensaje.getContenido() == null || mensaje.getContenido().isEmpty()) {
            throw new Exception("Contenido obligatorio");
        }
        if (mensaje.getContenido()== null) {
            throw new Exception("Estudiante obligatorio");
        }
        if (mensaje.getFechaHora() != null && mensaje.getFechaHora().isAfter(LocalDateTime.now())) {
            throw new Exception("Fecha no puede ser futura");
        }
        mensajeDAO.crear(mensaje);
    }

    @Override
    public Mensaje buscarMensajePorId(Long id) {
        return mensajeDAO.buscarPorId(id);
    }

    @Override
    public List<Mensaje> listarMensajes(int limit) {
        if (limit > 100) limit = 100;
        return mensajeDAO.listar(limit);
    }

    @Override
    public void actualizarMensaje(Mensaje mensaje) {
        mensajeDAO.actualizar(mensaje);
    }

    @Override
    public void eliminarMensaje(Long id) {
        mensajeDAO.eliminar(id);
    }

    @Override
    public List<Mensaje> buscarMensajesPorEstudiante(Estudiante estudiante) {
        return mensajeDAO.buscarMensajesPorEstudiante(estudiante);
    }

    @Override
    public List<Mensaje> buscarMensajesEntreEstudiantes(Estudiante estudiante1, Estudiante estudiante2) {
        return mensajeDAO.buscarMensajesEntreEstudiantes(estudiante1, estudiante2);
    }
}
