/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tinderitsonbd;

import com.mycompany.Service.EstudianteService;
import com.mycompany.Service.IEstudianteService;
import com.mycompany.Service.ILikeService;
import com.mycompany.Service.IMatchService;
import com.mycompany.Service.IMensajeService;
import com.mycompany.Service.LikeService;
import com.mycompany.Service.MatchService;
import com.mycompany.Service.MensajeService;
import com.mycompany.entities.Carrera;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import com.mycompany.entities.Match;
import com.mycompany.entities.Mensaje;
import com.mycompany.util.JpaUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Josel
 */
public class TinderItsonBD {

   
    public static void main(String[] args) {
        // Servicios
        IEstudianteService estudianteService = new EstudianteService();
        ILikeService likeService = new LikeService();
        IMatchService matchService = new MatchService();
        IMensajeService mensajeService = new MensajeService();

        try {
            // Carga de prueba: 50 estudiantes
            for (int i = 1; i <= 50; i++) {
                Estudiante e = new Estudiante();
                e.setNombre("Estudiante " + i);
                e.setDescripcion("Descripción " + i);
                e.setEdad(18 + (i % 10));
                e.setGenero(i % 2 == 0 ? "Masculino" : "Femenino");
                e.setCarrera(Carrera.values()[i % Carrera.values().length]);
                estudianteService.crearEstudiante(e);
            }
            System.out.println("50 estudiantes creados.");

            // 100 likes
            List<Estudiante> estudiantes = estudianteService.listarEstudiantes(50);
            for (int i = 0; i < 100; i++) {
                Like l = new Like();
                l.setTipo("Like");
                l.setLikeD(estudiantes.get(i % 50));
                l.setLikeR(estudiantes.get((i + 1) % 50));
                likeService.crearLike(l);
            }
            System.out.println("100 likes creados.");

            // 20 matches (generados automáticamente por likes mutuos)
            List<Match> matches = matchService.listarMatches(20);
            System.out.println("Matches listados: " + matches.size());

            // Mensajes (solo si hay match)
            for (Match m : matches) {
                Mensaje msg = new Mensaje();
                msg.setContenido("Hola, match!");
                msg.setFechaHora(LocalDateTime.now());
                msg.setmensaje(m.getEstudiante1());
                mensajeService.crearMensaje(msg);
            }
            System.out.println("Mensajes enviados.");

            // Demostración CRUD
            Estudiante e = estudianteService.buscarEstudiantePorId(1L);
            System.out.println("Estudiante encontrado: " + e.getNombre());
            e.setNombre("Actualizado");
            estudianteService.actualizarEstudiante(e);
            System.out.println("Estudiante actualizado.");

            // Consultas complejas
            List<Estudiante> porCarrera = estudianteService.buscarPorCarrera(Carrera.ING_SOFTWARE);
            System.out.println("Estudiantes en ING_SOFTWARE: " + porCarrera.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JpaUtil.close();
        }
    }
}

