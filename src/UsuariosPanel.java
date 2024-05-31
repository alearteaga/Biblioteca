import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class UsuariosPanel extends JPanel {
    private final UsuarioDAO usuarioDAO;
    private final JTable table;
    private final UsuarioTableModel tableModel;

    public UsuariosPanel(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setLayout(new BorderLayout());

        tableModel = new UsuarioTableModel(getUsuarios());
        table = new JTable(tableModel);

        JButton addButton = new JButton("Añadir Usuario");
        JButton editButton = new JButton("Editar Usuario");
        JButton deleteButton = new JButton("Eliminar Usuario");

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para añadir un usuario
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar un usuario
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un usuario
            }
        });
    }

    private List<Usuario> getUsuarios() {
        try {
            return usuarioDAO.obtenerUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
