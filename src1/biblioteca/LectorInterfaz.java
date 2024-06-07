package biblioteca;

import biblioteca.dialogs.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class LectorInterfaz extends JFrame {

    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private Usuario usuarioActual;

    private JButton btnVerCatalogo;
    private JButton btnPrestarLibro;
    private JButton btnDevolverLibro;
    private JButton btnMisPrestamos;

    public LectorInterfaz(Usuario usuario) {
        super("Interfaz de Lector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        this.usuarioActual = usuario;

        // Inicializar DAOs
        try {
            libroDAO = new LibroDAO(ConexionBD.getConnection());
            prestamoDAO = new PrestamoDAO(ConexionBD.getConnection());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Crear componentes de la interfaz
        btnVerCatalogo = new JButton("Ver Catálogo");
        btnPrestarLibro = new JButton("Prestar Libro");
        btnDevolverLibro = new JButton("Devolver Libro");
        btnMisPrestamos = new JButton("Mis Préstamos");

        // Agregar componentes a la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(btnVerCatalogo);
        panel.add(btnPrestarLibro);
        panel.add(btnDevolverLibro);
        panel.add(btnMisPrestamos);
        add(panel);

        // Escuchadores de eventos para los botones
        btnVerCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCatalogo();
            }
        });

        btnPrestarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarLibro();
            }
        });

        btnDevolverLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });

        btnMisPrestamos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verMisPrestamos();
            }
        });

        setVisible(true);
    }

    private void verCatalogo() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            LibroTableModel model = new LibroTableModel(libros);
            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Catálogo de Libros", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el catálogo de libros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prestarLibro() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            if (libros.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay libros disponibles para prestar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] opciones = new String[libros.size()];
            for (int i = 0; i < libros.size(); i++) {
                opciones[i] = libros.get(i).getTitulo();
            }
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione un libro:", "Prestar Libro", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null) {
                Libro libroSeleccionado = null;
                for (Libro libro : libros) {
                    if (libro.getTitulo().equals(seleccion)) {
                        libroSeleccionado = libro;
                        break;
                    }
                }
                if (libroSeleccionado != null) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdLibro(libroSeleccionado.getIdLibro());
                    prestamo.setIdUsuario(usuarioActual.getIdUsuario());
                    prestamo.setFechaPrestamo(new java.util.Date()); // Fecha actual
                    prestamoDAO.agregarPrestamo(prestamo);
                    JOptionPane.showMessageDialog(null, "Libro prestado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al prestar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void devolverLibro() {
        try {
            List<Prestamo> prestamos = (List<Prestamo>) prestamoDAO.obtenerPrestamoPorId(usuarioActual.getIdUsuario());
            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tiene libros prestados actualmente.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] opciones = new String[prestamos.size()];
            for (int i = 0; i < prestamos.size(); i++) {
                opciones[i] = prestamos.get(i).toString();
            }
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione el préstamo a devolver:", "Devolver Libro", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if (seleccion != null) {
                Prestamo prestamoSeleccionado = null;
                for (Prestamo prestamo : prestamos) {
                    if (prestamo.toString().equals(seleccion)) {
                        prestamoSeleccionado = prestamo;
                        break;
                    }
                }
                if (prestamoSeleccionado != null) {
                    prestamoDAO.eliminarPrestamo(prestamoSeleccionado.getIdPrestamo());
                    JOptionPane.showMessageDialog(null, "Libro devuelto correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al devolver el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verMisPrestamos() {
        try {
            List<Prestamo> prestamos = (List<Prestamo>) prestamoDAO.obtenerTodosLosPrestamos();
            PrestamoTableModel model = new PrestamoTableModel(prestamos);
            JTable table = new JTable(model);
            JOptionPane.showMessageDialog(null, new JScrollPane(table), "Mis Préstamos", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener mis préstamos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
