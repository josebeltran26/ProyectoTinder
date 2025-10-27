/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.DAO.HobbieDAO;
import com.mycompany.DAO.IHobbieDAO;
import com.mycompany.entities.Hobbie;
import java.util.List;

/**
 *
 * @author Josel
 */
public class HobbieService implements IHobbieService {

    private IHobbieDAO hobbieDAO = new HobbieDAO();

    @Override
    public void crearHobbie(Hobbie hobbie) throws Exception {
        if (hobbie.getNombre() == null || hobbie.getNombre().isEmpty()) {
            throw new Exception("Nombre obligatorio");
        }
        hobbieDAO.crear(hobbie);
    }

    @Override
    public Hobbie buscarHobbiePorId(Long id) {
        return hobbieDAO.buscarPorId(id);
    }

    @Override
    public List<Hobbie> listarHobbies(int limit) {
        if (limit > 100) {
            limit = 100;
        }
        return hobbieDAO.listar(limit);
    }

    @Override
    public void actualizarHobbie(Hobbie hobbie) throws Exception {
        if (hobbie.getNombre() == null || hobbie.getNombre().isEmpty()) {
            throw new Exception("Nombre obligatorio");
        }
        hobbieDAO.actualizar(hobbie);
    }

    @Override
    public void eliminarHobbie(Long id) {
        hobbieDAO.eliminar(id);
    }

    @Override
    public List<Hobbie> buscarPorNombre(String nombre) {
        return hobbieDAO.buscarPorNombre(nombre);
    }
}
