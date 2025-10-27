/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Correo;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ICorreoService {

    void crearCorreo(Correo correo) throws Exception;

    Correo buscarCorreoPorId(Long id);

    List<Correo> listarCorreos(int limit);

    void actualizarCorreo(Correo correo);

    void eliminarCorreo(Long id);

    List<Correo> buscarPorEstudiante(Estudiante estudiante);
}
