/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IHobbieDAO {

    void crear(Hobbie hobbie);

    Hobbie buscarPorId(Long id);

    List<Hobbie> listar(int limit);

    void actualizar(Hobbie hobbie);

    void eliminar(Long id);

    List<Hobbie> buscarPorNombre(String nombre);
}
