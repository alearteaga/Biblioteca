import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BibliotecaGUI extends JFrame {
    private Connection conexion;
    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;

    public BibliotecaGUI() {
        // Configuración de la ventana principal
        setTitle("Biblioteca Can Casacuberta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Conexión a la base de datos
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "usuario", "contraseña");
            libroDAO = new LibroDAO(conexion);
            usuarioDAO = new UsuarioDAO(conexion);
            prestamoDAO = new PrestamoDAO(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Libros", new LibrosPanel(libroDAO));
        tabbedPane.addTab("Usuarios", new UsuariosPanel(usuarioDAO));
        tabbedPane.addTab("Préstamos", new PrestamosPanel(prestamoDAO));

        // Añadir el panel principal a la ventana
        add(tabbedPane);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaGUI());
    }
}
