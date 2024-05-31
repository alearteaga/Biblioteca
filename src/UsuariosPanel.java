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
                AddUsuarioDialog dialog = new AddUsuarioDialog();
                dialog.setVisible(true);
                Usuario nuevoUsuario = dialog.getUsuario();
                if (nuevoUsuario != null) {
                    try {
                        usuarioDAO.agregarUsuario(nuevoUsuario);
                        actualizarTabla();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al agregar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Usuario usuarioSeleccionado = tableModel.getUsuarios().get(filaSeleccionada);
                    EditUsuarioDialog dialog = new EditUsuarioDialog(usuarioSeleccionado);
                    dialog.setVisible(true);
                    Usuario usuarioEditado = dialog.getUsuario();
                    if (usuarioEditado != null) {
                        try {
                            usuarioDAO.actualizarUsuario(usuarioEditado);
                            actualizarTabla();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Usuario usuarioSeleccionado = tableModel.getUsuarios().get(filaSeleccionada);
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este usuario?");
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            usuarioDAO.eliminarUsuario(usuarioSeleccionado.getId());
                            actualizarTabla();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private List<Usuario> getUsuarios() {
        try {
            return usuarioDAO.obtenerUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los usuarios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void actualizarTabla() {
        tableModel.setUsuarios(getUsuarios());
        tableModel.fireTableDataChanged();
    }
}
