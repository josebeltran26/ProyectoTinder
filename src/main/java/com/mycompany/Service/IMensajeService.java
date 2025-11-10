/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Mensaje;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IMensajeService {

    void crearMensaje(Mensaje mensaje) throws Exception;

    Mensaje buscarMensajePorId(Long id);

    List<Mensaje> listarMensajes(int limit);

    void actualizarMensaje(Mensaje mensaje);

    void eliminarMensaje(Long id);

    List<Mensaje> buscarMensajesPorEstudiante(Estudiante estudiante);

    List<Mensaje> buscarMensajesEntreEstudiantes(Estudiante estudiante1, Estudiante estudiante2);
}
