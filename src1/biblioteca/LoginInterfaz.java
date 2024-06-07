package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginInterfaz extends JFrame {

    private UsuarioDAO usuarioDAO;

    private JLabel lblCorreo;
    private JLabel lblContrasena;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;

    public LoginInterfaz() {
        super("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Inicializar el DAO de Usuario
          // Inicializar el DAO de Usuario
          try {
            usuarioDAO = new UsuarioDAO(ConexionBD.getConnection());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Crear componentes de la interfaz
        JPanel panel = new JPanel(new GridLayout(3, 2));
        lblCorreo = new JLabel("Correo:");
        lblContrasena = new JLabel("Contraseña:");
        txtCorreo = new JTextField();
        txtContrasena = new JPasswordField();
        btnIniciarSesion = new JButton("Iniciar Sesión");

        // Agregar componentes al panel
        panel.add(lblCorreo);
        panel.add(txtCorreo);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(new JLabel()); // Espacio en blanco
        panel.add(btnIniciarSesion);

        // Agregar panel a la ventana
        add(panel);

        // Escuchador de eventos para el botón Iniciar Sesión
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText();
                String contrasena = String.valueOf(txtContrasena.getPassword());
                try {
                    Usuario usuario = usuarioDAO.autenticarUsuario(correo, contrasena);
                    if (usuario != null) {
                        JOptionPane.showMessageDialog(null, "Bienvenido, " + usuario.getNombre() + " " + usuario.getApellidos() + "!", "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        // Verificar el rol del usuario y abrir la interfaz correspondiente
                        if (usuario.getRol().equals("Bibliotecario")) {
                            dispose(); // Cerrar la ventana de inicio de sesión
                            new BibliotecarioInterfaz(usuario); // Pasar el usuario
                        } else if (usuario.getRol().equals("Lector")) {
                            dispose(); // Cerrar la ventana de inicio de sesión
                            new LectorInterfaz(usuario);
                        } else {
                            JOptionPane.showMessageDialog(null, "Rol de usuario no reconocido.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginInterfaz();
            }
        });
    }
}
