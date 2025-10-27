/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Telefono;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ITelefonoDAO {

    void crear(Telefono telefono);

    Telefono buscarPorId(Long id);

    List<Telefono> listar(int limit);

    void actualizar(Telefono telefono);

    void eliminar(Long id);

    List<Telefono> buscarPorEstudiante(Estudiante estudiante);

    boolean existeTelefono(String numero);
}
