import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class BibliotecaGUI extends JFrame {
    private final LibroDAO libroDAO;
    private final UsuarioDAO usuarioDAO;
    private final PrestamoDAO prestamoDAO;

    public BibliotecaGUI(Connection conexion) {
        libroDAO = new LibroDAO(conexion);
        usuarioDAO = new UsuarioDAO(conexion);
        prestamoDAO = new PrestamoDAO(conexion);

        // Configuración de la ventana principal
        setTitle("Biblioteca Can Casacuberta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        try {
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "usuario", "contraseña");
            SwingUtilities.invokeLater(() -> new LoginFrame(conexion).setVisible(true));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
