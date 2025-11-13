/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Pantallas;

import com.mycompany.Service.EstudianteHobbieService;
import com.mycompany.Service.EstudianteService;
import com.mycompany.Service.IEstudianteHobbieService;
import com.mycompany.Service.IEstudianteService;
import com.mycompany.Service.ILikeService;
import com.mycompany.Service.IMatchService;
import com.mycompany.Service.LikeService;
import com.mycompany.Service.MatchService;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.EstudianteHobbie;
import com.mycompany.entities.Like;
import com.mycompany.entities.Match;
import com.mycompany.util.SessionManager;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author manue
 */
public class PerfilesDao extends javax.swing.JFrame {

    private final IEstudianteService estudianteService = new EstudianteService();
    private final ILikeService likeService = new LikeService();
    private final IMatchService matchService = new MatchService();
    private final IEstudianteHobbieService estudianteHobbieService = new EstudianteHobbieService();

    private List<Estudiante> perfilesDisponibles;
    private int indice = 0;
    private Estudiante estudianteLogueado;

    /**
     * Creates new form PerfilesDao
     */
    public PerfilesDao() {
        initComponents();
        this.estudianteLogueado = SessionManager.getEstudianteLogueado();
        if (this.estudianteLogueado == null) {
            new IniciarSesionDao().setVisible(true);
            this.dispose();
            return;
        }
        cargarPerfiles();
        mostrarPerfil();
    }

    private void cargarPerfiles() {
        try {
            perfilesDisponibles = estudianteService.buscarPerfilesParaSwipe(estudianteLogueado.getId(), 10);
            indice = 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar perfiles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPerfil() {
        if (perfilesDisponibles == null || perfilesDisponibles.isEmpty() || indice >= perfilesDisponibles.size()) {
            lblNombre.setText("No hay más perfiles disponibles.");
            lblCarrera.setText("");
            Hobbies.setText("");
            lblDescripcion.setText("");
            lblFoto.setIcon(null);
            lblFoto.setText("");
            btnLike.setEnabled(false);
            btnSkip.setEnabled(false);
            
            return;
        }

        Estudiante e = perfilesDisponibles.get(indice);

        lblNombre.setText(e.getNombre() + " (" + e.getEdad() + " años)");
        lblCarrera.setText(e.getCarrera() != null ? "Carrera: " + e.getCarrera().name() : "Carrera: N/A");
        lblDescripcion.setText(e.getDescripcion() != null && !e.getDescripcion().isEmpty() ? "Descripción: " + e.getDescripcion() : "Descripción: N/A"); // CORRECCIÓN: Mostrar descripción/intereses

        try {
            List<EstudianteHobbie> estudianteHobbies = estudianteHobbieService.buscarPorEstudiante(e);
            String hobbieStr = "Hobbies: " + estudianteHobbies.stream().filter(eh -> eh.getHobbie() != null).map(eh -> eh.getHobbie().getNombre()).reduce((s1, s2) -> s1 + ", " + s2).orElse("Sin hobbies");
            Hobbies.setText(hobbieStr);
        } catch (Exception ex) {
            Hobbies.setText("Error al cargar hobbies");
        }

        String fotoUrl = e.getFotoUsuarioUrl();
        if (fotoUrl != null && !fotoUrl.isEmpty()) {
            try {
                ImageIcon icono = new ImageIcon(fotoUrl);
                Image img = icono.getImage().getScaledInstance(
                        lblFoto.getWidth(),
                        lblFoto.getHeight(),
                        Image.SCALE_SMOOTH
                );
                lblFoto.setIcon(new ImageIcon(img));
                lblFoto.setText("");
            } catch (Exception ex) {
                lblFoto.setText("Error al cargar foto");
                lblFoto.setIcon(null);
            }
        } else {
            lblFoto.setText("Sin Foto");
            lblFoto.setIcon(null);
        }

        boolean existeMatch = matchService.existeMatch(estudianteLogueado, e);
        boolean likeDado = likeService.buscarLikeDado(estudianteLogueado, e) != null;

        btnLike.setEnabled(!likeDado);
        btnSkip.setEnabled(true);

        
    }

    private void siguientePerfil() {
        indice++;
        if (indice >= perfilesDisponibles.size()) {
            cargarPerfiles();
        }
        mostrarPerfil();
    }
        private void AtrasPerfil() {
        indice--;
        if (indice <=0) {
            cargarPerfiles();
        }
        mostrarPerfil();
    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        btnLike = new javax.swing.JButton();
        btnSkip = new javax.swing.JButton();
        Hobbies = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        btnMensaje = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblNombre.setText("jLabel1");

        lblCarrera.setText("jLabel1");

        btnLike.setForeground(new java.awt.Color(255, 0, 0));
        btnLike.setText("  ❤️");
        btnLike.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLikeActionPerformed(evt);
            }
        });

        btnSkip.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnSkip.setText("->");
        btnSkip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkipActionPerformed(evt);
            }
        });

        Hobbies.setText("jLabel1");

        btnMensaje.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnMensaje.setForeground(new java.awt.Color(153, 153, 153));
        btnMensaje.setText("Mensaje");
        btnMensaje.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajeActionPerformed(evt);
            }
        });

        btnQuitar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnQuitar.setText("<-");
        btnQuitar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        lblDescripcion.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(btnMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(btnSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                        .addComponent(lblCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Hobbies)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addGap(18, 18, 18)
                .addComponent(lblCarrera)
                .addGap(11, 11, 11)
                .addComponent(Hobbies)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSkip, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLikeActionPerformed
        if (perfilesDisponibles == null || indice >= perfilesDisponibles.size()) {
            return;
        }
        Estudiante perfilVisto = perfilesDisponibles.get(indice);

        try {
            Like nuevoLike = new Like();
            nuevoLike.setEmisor(estudianteLogueado);
            nuevoLike.setReceptor(perfilVisto);
            likeService.crearLike(nuevoLike);

            if (matchService.existeMatch(estudianteLogueado, perfilVisto)) {
                JOptionPane.showMessageDialog(this, "match Puedes chatear desde la lista de Likes.", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "like Espera un Match.","", JOptionPane.INFORMATION_MESSAGE);
            }

            new ListaLikesDao().setVisible(true);
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar like: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLikeActionPerformed

    private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkipActionPerformed
        siguientePerfil();

    }//GEN-LAST:event_btnSkipActionPerformed

    private void btnMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajeActionPerformed
        if (perfilesDisponibles == null || indice >= perfilesDisponibles.size()) {
            return;
        }
        Estudiante perfilVisto = perfilesDisponibles.get(indice);

        try {
            List<Match> matches = matchService.buscarMatchesPorEstudiante(estudianteLogueado);
            Match matchEncontrado = matches.stream().filter(m -> (m.getEstudiante1().getId().equals(perfilVisto.getId()) || m.getEstudiante2().getId().equals(perfilVisto.getId()))).findFirst().orElse(null);

            if (matchEncontrado != null) {
                new ChatDao(matchEncontrado).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Aún no hay Match para chatear.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar el chat: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMensajeActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
       AtrasPerfil();
    }//GEN-LAST:event_btnQuitarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hobbies;
    private javax.swing.JButton btnLike;
    private javax.swing.JButton btnMensaje;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSkip;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
