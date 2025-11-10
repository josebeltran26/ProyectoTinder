/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.Service;

import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import java.util.List;

/**
 *
 * @author Josel
 */
public interface ILikeService {

    void crearLike(Like like) throws Exception;

    Like buscarLikePorId(Long id);

    List<Like> listarLikes(int limit);

    void actualizarLike(Like like);

    void eliminarLike(Long id);

    List<Like> buscarLikesDadosPorEstudiante(Estudiante estudiante);

    List<Like> buscarLikesRecibidosPorEstudiante(Estudiante estudiante);
}
