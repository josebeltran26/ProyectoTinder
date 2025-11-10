/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Mensaje;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IMensajeDAO {

    void crear(Mensaje mensaje);

    Mensaje buscarPorId(Long id);

    List<Mensaje> listar(int limit);

    void actualizar(Mensaje mensaje);

    void eliminar(Long id);

    List<Mensaje> buscarMensajesPorEstudiante(Estudiante estudiante);

    List<Mensaje> buscarMensajesEntreEstudiantes(Estudiante estudiante1, Estudiante estudiante2);
}
