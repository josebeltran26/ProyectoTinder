/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Correo;
import com.mycompany.entities.Estudiante;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ICorreoDAO {

    void crear(Correo correo);

    Correo buscarPorId(Long id);

    List<Correo> listar(int limit);

    void actualizar(Correo correo);

    void eliminar(Long id);

    List<Correo> buscarPorEstudiante(Estudiante estudiante);

    boolean existeCorreo(String correo);
}
