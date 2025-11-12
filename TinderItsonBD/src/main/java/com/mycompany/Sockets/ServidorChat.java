/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Sockets;

import com.mycompany.DAO.MensajeDAO;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Mensaje;
import com.mycompany.entities.Match;
import com.mycompany.DAO.MatchDAO;
import com.mycompany.DAO.EstudianteDAO;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

/**
 *
 * @author Josel
 */

public class ServidorChat {

    private static final int PUERTO = 9090;
    private static Map<Long, Map<Long, PrintWriter>> escritoresPorMatch = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat TinderItson iniciado en el puerto " + PUERTO);
        try (ServerSocket listener = new ServerSocket(PUERTO)) {
            while (true) {
                new ManejadorCliente(listener.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente extends Thread {

        private Socket socket;
        private Long estudianteId;
        private Long matchId;
        private PrintWriter out;

        private final MensajeDAO mensajeDAO = new MensajeDAO();
        private final MatchDAO matchDAO = new MatchDAO();
        private final EstudianteDAO estudianteDAO = new EstudianteDAO();

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String line = in.readLine();
                if (line != null && line.startsWith("CONECTAR:")) {
                    String[] partes = line.split(":");
                    estudianteId = Long.parseLong(partes[1]);
                    matchId = Long.parseLong(partes[2]);

                    synchronized (escritoresPorMatch) {
                        escritoresPorMatch.computeIfAbsent(matchId, k -> new HashMap<>()).put(estudianteId, out);
                        System.out.println("Cliente conectado: Estudiante ID " + estudianteId + ", Match ID " + matchId);
                    }
                } else {
                    return;
                }

                while ((line = in.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }

                    guardarMensaje(line);
                    reenviarMensaje(line);
                }
            } catch (Exception e) {
                System.err.println("Cliente desconectado o error: " + e.getMessage());
            } finally {
                if (matchId != null && estudianteId != null) {
                    synchronized (escritoresPorMatch) {
                        Map<Long, PrintWriter> matchClients = escritoresPorMatch.get(matchId);
                        if (matchClients != null) {
                            matchClients.remove(estudianteId);
                        }
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                System.out.println("Conexión cerrada para Estudiante ID " + estudianteId);
            }
        }
        }

        private void guardarMensaje(String contenido) {
            try {
                Match match = matchDAO.buscarPorId(matchId);
                Estudiante emisor = estudianteDAO.buscarPorId(estudianteId);

                if (match != null && emisor != null) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.setContenido(contenido);
                    mensaje.setEmisor(emisor);
                    mensaje.setMatch(match);
                    mensaje.setFechaHora(LocalDateTime.now());
                    mensajeDAO.crear(mensaje);
                }
            } catch (Exception e) {
                System.err.println("Error al guardar mensaje en DB: " + e.getMessage());
            }
        }

        private void reenviarMensaje(String contenido) {
            String mensajeCompleto = estudianteId + ":" + contenido;
            synchronized (escritoresPorMatch) {
                Map<Long, PrintWriter> matchClients = escritoresPorMatch.get(matchId);
                if (matchClients != null) {
                    for (PrintWriter escritor : matchClients.values()) {
                        escritor.println(mensajeCompleto);
                    }
                }
            }
        }
    }
}
