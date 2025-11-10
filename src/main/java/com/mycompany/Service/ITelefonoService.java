/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Telefono;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ITelefonoService {

    void crearTelefono(Telefono telefono) throws Exception;

    Telefono buscarTelefonoPorId(Long id);

    List<Telefono> listarTelefonos(int limit);

    void actualizarTelefono(Telefono telefono);

    void eliminarTelefono(Long id);

    List<Telefono> buscarPorEstudiante(Estudiante estudiante);
}
