import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;

public class UsuariosPanel extends JPanel {
    private final UsuarioDAO usuarioDAO;
    private final JTextField nombreField;
    private final JTextField apellidosField;
    private final JTextField emailField;
    private final JTextField telefonoField;
    private final JTextField rolField;
    private final JTextField fechaRegistroField;

    public UsuariosPanel(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        setLayout(new BorderLayout());

        // Formulario de usuario
        JPanel formularioPanel = new JPanel(new GridLayout(7, 2));
        formularioPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        formularioPanel.add(nombreField);
        formularioPanel.add(new JLabel("Apellidos:"));
        apellidosField = new JTextField();
        formularioPanel.add(apellidosField);
        formularioPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formularioPanel.add(emailField);
        formularioPanel.add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        formularioPanel.add(telefonoField);
        formularioPanel.add(new JLabel("Rol:"));
        rolField = new JTextField();
        formularioPanel.add(rolField);
        formularioPanel.add(new JLabel("Fecha Registro:"));
        fechaRegistroField = new JTextField();
        formularioPanel.add(fechaRegistroField);

        // Botón de agregar usuario
        JButton agregarButton = new JButton("Agregar Usuario");
        agregarButton.addActionListener((ActionEvent e) -> {
            agregarUsuario();
        });
        formularioPanel.add(agregarButton);

        add(formularioPanel, BorderLayout.NORTH);

        // Tabla de usuarios
        JTable usuariosTable = new JTable(new UsuariosTableModel(usuarioDAO));
        add(new JScrollPane(usuariosTable), BorderLayout.CENTER);
    }

    private void agregarUsuario() {
        String nombre = nombreField.getText();
        String apellidos = apellidosField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String rol = rolField.getText();
        String fechaRegistro = fechaRegistroField.getText();
        String contraseña = JOptionPane.showInputDialog(this, "Ingrese la contraseña:");

        Usuario usuario = new Usuario(0, nombre, apellidos, email, telefono, rol, fechaRegistro, contraseña);

        try {
            usuarioDAO.agregarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
