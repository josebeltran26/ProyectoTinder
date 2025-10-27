/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface IHobbieService {

    void crearHobbie(Hobbie hobbie) throws Exception;

    Hobbie buscarHobbiePorId(Long id);

    List<Hobbie> listarHobbies(int limit);

    void actualizarHobbie(Hobbie hobbie) throws Exception;

    void eliminarHobbie(Long id);

    List<Hobbie> buscarPorNombre(String nombre);
}
