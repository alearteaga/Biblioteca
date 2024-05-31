import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private final Connection conexion;

    public LoginFrame(Connection conexion) {
        this.conexion = conexion;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Iniciar sesión");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contrasena = new String(passField.getPassword());

                // Verificar las credenciales en la base de datos
                try {
                    String query = "SELECT * FROM Credenciales WHERE usuario = ? AND contrasena = ?";
                    PreparedStatement statement = conexion.prepareStatement(query);
                    statement.setString(1, usuario);
                    statement.setString(2, contrasena);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        // Si las credenciales son válidas, abrir la ventana principal
                        new BibliotecaGUI(conexion).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    statement.close();
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al verificar las credenciales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
