import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaGUI extends JFrame {
    private final LibrosPanel librosPanel;
    private final PrestamosPanel prestamosPanel;
    private final UsuariosPanel usuariosPanel;

    public BibliotecaGUI(LibroDAO libroDAO, PrestamoDAO prestamoDAO, UsuarioDAO usuarioDAO, Connection conexion) {
        // Configuración de la ventana principal
        setTitle("Biblioteca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicialización de los paneles
        librosPanel = new LibrosPanel(libroDAO);
        prestamosPanel = new PrestamosPanel(prestamoDAO);
        usuariosPanel = new UsuariosPanel(usuarioDAO);

        // Creación del panel de contenido
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.add(librosPanel, "Libros");
        contentPanel.add(prestamosPanel, "Préstamos");
        contentPanel.add(usuariosPanel, "Usuarios");

        // Añadir el panel de contenido al marco
        add(contentPanel, BorderLayout.CENTER);

        // Creación del menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menú");

        JMenuItem librosMenuItem = new JMenuItem("Libros");
        JMenuItem prestamosMenuItem = new JMenuItem("Préstamos");
        JMenuItem usuariosMenuItem = new JMenuItem("Usuarios");

        librosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "Libros");
            }
        });

        prestamosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "Préstamos");
            }
        });

        usuariosMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, "Usuarios");
            }
        });

        menu.add(librosMenuItem);
        menu.add(prestamosMenuItem);
        menu.add(usuariosMenuItem);
        menuBar.add(menu);

        // Agregar el botón "Administrador" al menú
        JButton adminButton = new JButton("Administrador");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminFrame(conexion).setVisible(true);
            }
        });
        menuBar.add(Box.createHorizontalGlue()); // Alinear el botón a la derecha
        menuBar.add(adminButton);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        // Asumiendo que los DAOs han sido implementados y configurados correctamente
        LibroDAO libroDAO = new LibroDAO();
        PrestamoDAO prestamoDAO = new PrestamoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection conexion = obtenerConexion(); // Asegúrate de obtener la conexión aquí
                    BibliotecaGUI frame = new BibliotecaGUI(libroDAO, prestamoDAO, usuarioDAO, conexion);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Método ficticio para obtener la conexión a la base de datos
    private static Connection obtenerConexion() {
        // Lógica para obtener la conexión
        return null;
    }
}
