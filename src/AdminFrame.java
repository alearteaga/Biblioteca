import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminFrame extends JFrame {
    private final Connection conexion;

    public AdminFrame(Connection conexion) {
        this.conexion = conexion;
        setTitle("Administrador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        JButton addButton = new JButton("Agregar Usuario");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(new JLabel());
        panel.add(addButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contrasena = new String(passField.getPassword());

                // Agregar usuario a la base de datos
                try {
                    String query = "INSERT INTO Credenciales (usuario, contrasena) VALUES (?, ?)";
                    PreparedStatement statement = conexion.prepareStatement(query);
                    statement.setString(1, usuario);
                    statement.setString(2, contrasena);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
