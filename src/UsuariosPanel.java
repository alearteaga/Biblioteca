import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UsuariosPanel extends JPanel {
    private final UsuarioDAO usuarioDAO;
    private JTable usuariosTable;

    public UsuariosPanel(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setLayout(new BorderLayout());

        try {
            UsuarioTableModel usuarioTableModel = new UsuarioTableModel(usuarioDAO.obtenerUsuarios());
            usuariosTable = new JTable(usuarioTableModel);
            add(new JScrollPane(usuariosTable), BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
