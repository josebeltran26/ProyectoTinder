package Pantallas;

import com.mycompany.Service.IMensajeService;
import com.mycompany.Service.MensajeService;
import com.mycompany.Sockets.ClienteChat;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Match;
import com.mycompany.entities.Mensaje;
import com.mycompany.util.SessionManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author manue
 */
public class ChatDao extends JFrame {

    private final IMensajeService mensajeService = new MensajeService();
    private ClienteChat clienteChat;

    private final Match match;
    private final Estudiante estudianteLogueado;
    private final Estudiante otroEstudiante;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private JPanel panelMensajes;
    private JScrollPane scrollPane;
    private JTextField campoTexto;
    private JButton btnEnviar;

    public ChatDao(Match match) {
        this.match = match;
        this.estudianteLogueado = SessionManager.getEstudianteLogueado();

        if (this.estudianteLogueado == null) {
            JOptionPane.showMessageDialog(this, "Sesión no iniciada.", "Error", JOptionPane.ERROR_MESSAGE);
            this.otroEstudiante = null;
            return;
        }

        this.otroEstudiante = match.getEstudiante1().getId().equals(estudianteLogueado.getId())
                ? match.getEstudiante2()
                : match.getEstudiante1();

        setTitle("Chat con " + otroEstudiante.getNombre() + " (Tiempo Real)");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelMensajes = new JPanel();
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));
        panelMensajes.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(panelMensajes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInput = new JPanel(new BorderLayout(5, 5));
        panelInput.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        campoTexto = new JTextField();
        btnEnviar = new JButton("Enviar");
        panelInput.add(campoTexto, BorderLayout.CENTER);
        panelInput.add(btnEnviar, BorderLayout.EAST);
        add(panelInput, BorderLayout.SOUTH);

        btnEnviar.addActionListener(e -> enviarMensaje());
        campoTexto.addActionListener(e -> enviarMensaje());

        cargarHistorial();

        try {
            clienteChat = new ClienteChat(this, estudianteLogueado.getId(), match.getId());
            clienteChat.conectar();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al conectar al servidor de chat. Asegúrate que ServidorChat.java está corriendo.", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            btnEnviar.setEnabled(false);
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (clienteChat != null) {
                    clienteChat.desconectar();
                }
            }
        });
    }

    private void cargarHistorial() {
        if (estudianteLogueado == null || otroEstudiante == null) {
            return;
        }
        new Thread(() -> {
            try {
                List<Mensaje> mensajes = mensajeService.buscarMensajesEntreEstudiantes(estudianteLogueado, otroEstudiante);

                SwingUtilities.invokeLater(() -> {
                    panelMensajes.removeAll();

                    for (Mensaje mensaje : mensajes) {
                        boolean esTuyo = mensaje.getEmisor().getId().equals(estudianteLogueado.getId());
                        agregarBurbuja(mensaje.getContenido(), esTuyo, mensaje.getFechaHora());
                    }

                    JScrollBar sb = scrollPane.getVerticalScrollBar();
                    sb.setValue(sb.getMaximum());
                });

            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Error al cargar historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            }
        }).start();
    }

    private void enviarMensaje() {
        String texto = campoTexto.getText().trim();
        if (texto.isEmpty() || clienteChat == null || !btnEnviar.isEnabled()) {
            return;
        }
        clienteChat.enviarMensaje(texto);
        campoTexto.setText("");
    }

    public void mostrarMensajeRecibido(String contenido, boolean esTuyo, LocalDateTime fechaHora) {
        agregarBurbuja(contenido, esTuyo, fechaHora);

        SwingUtilities.invokeLater(() -> {
            JScrollBar sb = scrollPane.getVerticalScrollBar();
            sb.setValue(sb.getMaximum());
        });
    }

    private void agregarBurbuja(String mensaje, boolean esTuyo, LocalDateTime fechaHora) {

        JPanel burbujaWrapper = new JPanel(new BorderLayout());
        burbujaWrapper.setBackground(Color.WHITE);
        burbujaWrapper.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel bubbleContent = new JPanel();
        bubbleContent.setLayout(new BoxLayout(bubbleContent, BoxLayout.Y_AXIS));

        JTextArea messageText = new JTextArea(mensaje);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messageText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messageText.setMaximumSize(new Dimension(250, Short.MAX_VALUE));

        JLabel timeLabel = new JLabel(fechaHora.format(timeFormatter));
        timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        timeLabel.setForeground(Color.DARK_GRAY);

        if (esTuyo) {
            messageText.setBackground(new Color(175, 238, 238));
            bubbleContent.setBackground(new Color(175, 238, 238));

            messageText.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            bubbleContent.setAlignmentX(Component.RIGHT_ALIGNMENT);

            bubbleContent.add(messageText);
            bubbleContent.add(timeLabel);

            burbujaWrapper.add(bubbleContent, BorderLayout.EAST);
        } else {
            messageText.setBackground(new Color(230, 230, 230));
            bubbleContent.setBackground(new Color(230, 230, 230));

            messageText.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            bubbleContent.setAlignmentX(Component.LEFT_ALIGNMENT);

            bubbleContent.add(messageText);
            bubbleContent.add(timeLabel);

            burbujaWrapper.add(bubbleContent, BorderLayout.WEST);
        }

        panelMensajes.add(burbujaWrapper);
        panelMensajes.revalidate();
        panelMensajes.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
