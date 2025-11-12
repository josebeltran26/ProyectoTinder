/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import com.mycompany.Service.ILikeService;
import com.mycompany.Service.LikeService;
import com.mycompany.Service.IMatchService;
import com.mycompany.Service.MatchService;
import com.mycompany.entities.Estudiante;
import com.mycompany.entities.Like;
import com.mycompany.entities.Match;
import com.mycompany.util.SessionManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaLikesDao extends javax.swing.JFrame {

    private final ILikeService likeService = new LikeService();
    private final IMatchService matchService = new MatchService();
    private Estudiante estudianteLogueado;
    private List<Like> likesDados;
    private Map<Integer, Estudiante> perfilesLikeadosMap;

    private javax.swing.JList<String> ListaLikes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton btnVolverPerfiles;

    public ListaLikesDao() {
        initComponents();
        this.estudianteLogueado = SessionManager.getEstudianteLogueado();
        if (this.estudianteLogueado == null) {
            new IniciarSesionDao().setVisible(true);
            this.dispose();
            return;
        }
        cargarLikes();
    }

    private void cargarLikes() {
        try {
            likesDados = likeService.buscarLikesDadosPorEstudiante(estudianteLogueado);
            DefaultListModel<String> model = new DefaultListModel<>();
            perfilesLikeadosMap = new HashMap<>();

            if (likesDados.isEmpty()) {
                model.addElement("Aún no has dado Like a nadie.");
            } else {
                for (int i = 0; i < likesDados.size(); i++) {
                    Like like = likesDados.get(i);
                    Estudiante receptor = like.getReceptor();

                    perfilesLikeadosMap.put(i, receptor);

                    String estado = matchService.existeMatch(estudianteLogueado, receptor) ? " (MATCH - CHATEAR)" : " (Pendiente)";
                    model.addElement(receptor.getNombre() + estado);
                }
            }

            ListaLikes.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar Likes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ListaLikesMouseClicked(java.awt.event.MouseEvent evt) {
        int index = ListaLikes.getSelectedIndex();
        if (index >= 0 && !likesDados.isEmpty() && evt.getClickCount() == 2) {
            Estudiante perfilSeleccionado = perfilesLikeadosMap.get(index);

            try {
                if (matchService.existeMatch(estudianteLogueado, perfilSeleccionado)) {
                    List<Match> matches = matchService.buscarMatchesPorEstudiante(estudianteLogueado);
                    Match matchEncontrado = matches.stream()
                            .filter(m -> (m.getEstudiante1().getId().equals(perfilSeleccionado.getId()) || m.getEstudiante2().getId().equals(perfilSeleccionado.getId())))
                            .findFirst()
                            .orElse(null);

                    if (matchEncontrado != null) {
                        new ChatDao(matchEncontrado).setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Aún no hay Match. Vuelve a Perfiles (Swipe) para ver más.", "Pendiente", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error de navegación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnVolverPerfilesActionPerformed(java.awt.event.ActionEvent evt) {
        new PerfilesDao().setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
    }
}
