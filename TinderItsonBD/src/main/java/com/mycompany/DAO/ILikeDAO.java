/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.DAO;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ILikeDAO {

    void crear(Like like);

    Like buscarPorId(Long id);

    List<Like> listar(int limit);

    void actualizar(Like like);

    void eliminar(Long id);

    List<Like> buscarLikesDadosPorEstudiante(Estudiante estudiante);

    List<Like> buscarLikesRecibidosPorEstudiante(Estudiante estudiante);

    boolean existeLikeMutuo(Estudiante estudiante1, Estudiante estudiante2);
}
