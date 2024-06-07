package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BibliotecarioInterfaz extends JFrame {

    private LibroDAO libroDAO;
    private UsuarioDAO usuarioDAO;
    private PrestamoDAO prestamoDAO;
    private Usuario usuarioActual;

    private JButton btnGestionLibros;
    private JButton btnGestionUsuarios;
    private JButton btnGestionPrestamos;

    public BibliotecarioInterfaz(Usuario usuario) {
        super("Interfaz de Bibliotecario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        this.usuarioActual = usuario;

        // Inicializar DAOs
        try {
            libroDAO = new LibroDAO(ConexionBD.getConnection());
            usuarioDAO = new UsuarioDAO(ConexionBD.getConnection());
            prestamoDAO = new PrestamoDAO(ConexionBD.getConnection());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Crear componentes de la interfaz
        btnGestionLibros = new JButton("Gestión de Libros");
        btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionPrestamos = new JButton("Gestión de Préstamos");

        // Agregar componentes a la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(btnGestionLibros);
        panel.add(btnGestionUsuarios);
        panel.add(btnGestionPrestamos);
        add(panel);

        // Escuchadores de eventos para los botones
        btnGestionLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarLibros();
            }
        });

        btnGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarUsuarios();
            }
        });

        btnGestionPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestionarPrestamos();
            }
        });

        setVisible(true);
    }

    private void gestionarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            LibroTableModel model = new LibroTableModel(libros);
            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Gestión de Libros", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de libros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
            UsuarioTableModel model = new UsuarioTableModel(usuarios);
            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Gestión de Usuarios", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerTodosLosPrestamos();
            PrestamoTableModel model = new PrestamoTableModel(prestamos);
            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Gestión de Préstamos", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de préstamos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
