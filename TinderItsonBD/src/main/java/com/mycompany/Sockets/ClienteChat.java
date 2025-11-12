/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Sockets;

import Pantallas.ChatDao;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

import javax.swing.SwingUtilities;

/**
 *
 * @author Josel
 */
public class ClienteChat {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    private Socket socket;
    private PrintWriter out;
    private ChatDao ui;
    private Long estudianteId;
    private Long matchId;

    public ClienteChat(ChatDao ui, Long estudianteId, Long matchId) {
        this.ui = ui;
        this.estudianteId = estudianteId;
        this.matchId = matchId;
    }

    public void conectar() throws IOException {
        socket = new Socket(SERVER_IP, SERVER_PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("CONECTAR:" + estudianteId + ":" + matchId);

        new Thread(() -> escucharMensajes(in)).start();
    }

    public void enviarMensaje(String mensaje) {
        if (out != null) {
            out.println(mensaje);
        }
    }

    public void desconectar() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            }
    }

    private void escucharMensajes(BufferedReader in) {
        try {
            String mensajeRecibido;
            while ((mensajeRecibido = in.readLine()) != null) {
                String[] partes = mensajeRecibido.split(":", 2);

                if (partes.length == 2) {
                    Long idEmisor = Long.parseLong(partes[0]);
                    String contenido = partes[1];

                    boolean esTuyo = idEmisor.equals(estudianteId);

                    SwingUtilities.invokeLater(() -> {
                        ui.mostrarMensajeRecibido(contenido, esTuyo, LocalDateTime.now());
                    });
                }
            }
        } catch (IOException e) {
        } finally {
            desconectar();
        }
    }
}
