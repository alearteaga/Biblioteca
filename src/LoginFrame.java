import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private final UsuarioDAO usuarioDAO;
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginFrame(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Usuario o Email:");
        usernameField = new JTextField(15);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        buttonPanel.add(loginButton);

        add(usernamePanel);
        add(passwordPanel);
        add(buttonPanel);
    }

    private void iniciarSesion() {
        String usernameOrEmail = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Usuario usuario = usuarioDAO.iniciarSesion(usernameOrEmail, password);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
