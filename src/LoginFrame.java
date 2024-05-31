import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final UsuarioDAO usuarioDAO;

    public LoginFrame(Connection conexion) {
        // Configuración de la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.usuarioDAO = new UsuarioDAO(conexion);

        // Panel de formulario de inicio de sesión
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        loginPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener((ActionEvent e) -> {
            iniciarSesion();
        });
        loginPanel.add(loginButton);

        add(loginPanel, BorderLayout.CENTER);
    }

    private void iniciarSesion() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Usuario usuario = usuarioDAO.iniciarSesion(email, password);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar la ventana de inicio de sesión
                new BibliotecaGUI(null); // Abrir la ventana principal de la biblioteca
            } else {
                JOptionPane.showMessageDialog(this, "Email o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
