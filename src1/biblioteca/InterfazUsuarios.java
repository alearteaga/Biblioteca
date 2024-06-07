package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazUsuarios extends JFrame {
    private UsuarioDAO usuarioDAO;
    private JTextField idField, numeroField, apellidosField, correoField, telefonoField, rolField, fechaRegistroField;
    private JTable usuariosTable;

    public InterfazUsuarios(Connection connection) {
        usuarioDAO = new UsuarioDAO(connection);

        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2));

        idField = new JTextField();
        numeroField = new JTextField();
        apellidosField = new JTextField();
        correoField = new JTextField();
        telefonoField = new JTextField();
        rolField = new JTextField();
        fechaRegistroField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Número:"));
        formPanel.add(numeroField);
        formPanel.add(new JLabel("Apellidos:"));
        formPanel.add(apellidosField);
        formPanel.add(new JLabel("Correo:"));
        formPanel.add(correoField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);
        formPanel.add(new JLabel("Rol:"));
        formPanel.add(rolField);
        formPanel.add(new JLabel("Fecha de Registro:"));
        formPanel.add(fechaRegistroField);

        // Botones
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numeroField.getText().isEmpty() || apellidosField.getText().isEmpty() || correoField.getText().isEmpty() ||
                        telefonoField.getText().isEmpty() || rolField.getText().isEmpty() || fechaRegistroField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Usuario usuario = new Usuario(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(numeroField.getText()),
                            apellidosField.getText(),
                            correoField.getText(),
                            telefonoField.getText(),
                            rolField.getText(),
                            java.sql.Date.valueOf(fechaRegistroField.getText())
                    );
                    usuarioDAO.agregarUsuario(usuario);
                    cargarUsuarios();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Modificar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (numeroField.getText().isEmpty() || apellidosField.getText().isEmpty() || correoField.getText().isEmpty() ||
                        telefonoField.getText().isEmpty() || rolField.getText().isEmpty() || fechaRegistroField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Usuario usuario = new Usuario(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(numeroField.getText()),
                            apellidosField.getText(),
                            correoField.getText(),
                            telefonoField.getText(),
                            rolField.getText(),
                            java.sql.Date.valueOf(fechaRegistroField.getText())
                    );
                    usuarioDAO.modificarUsuario(usuario);
                    cargarUsuarios();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    usuarioDAO.eliminarUsuario(id);
                    cargarUsuarios();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(deleteButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tabla
        usuariosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(usuariosTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        cargarUsuarios();
        setVisible(true);
    }

    private void cargarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            usuariosTable.setModel(new UsuarioTableModel(usuarios));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
