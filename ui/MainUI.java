import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "My7Pass@Word_9_8A_zE");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        SwingUtilities.invokeLater(() -> {
            new InterfazBibliotecario(connection);
        });

        SwingUtilities.invokeLater(() -> {
            new InterfazLector(connection);
        });
    }

    public static class InterfazBibliotecario extends JFrame {
        private JButton btnAgregarLibro, btnModificarLibro, btnEliminarLibro;
        private JButton btnVerPrestamosActivos, btnVerHistorialPrestamos;
        private JButton btnGestionarMultas;
        private Connection connection;

        public InterfazBibliotecario(Connection connection) {
            this.connection = connection;
            setTitle("Interfaz de Bibliotecario");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            btnAgregarLibro = new JButton("Agregar Libro");
            btnModificarLibro = new JButton("Modificar Libro");
            btnEliminarLibro = new JButton("Eliminar Libro");
            btnVerPrestamosActivos = new JButton("Ver Préstamos Activos");
            btnVerHistorialPrestamos = new JButton("Ver Historial de Préstamos");
            btnGestionarMultas = new JButton("Gestionar Multas");

            panel.add(btnAgregarLibro);
            panel.add(btnModificarLibro);
            panel.add(btnEliminarLibro);
            panel.add(btnVerPrestamosActivos);
            panel.add(btnVerHistorialPrestamos);
            panel.add(btnGestionarMultas);

            btnAgregarLibro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Abre una nueva ventana para agregar un libro
                    new VentanaAgregarLibro(connection);
                }
            });

            // Agrega el resto de los ActionListener para los otros botones

            add(panel);
            setVisible(true);
        }
    }

    public static class InterfazLector extends JFrame {
        private JButton btnBuscarLibros, btnConsultarHistorial;
        private Connection connection;

        public InterfazLector(Connection connection) {
            this.connection = connection;
            setTitle("Interfaz de Lector");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            btnBuscarLibros = new JButton("Buscar Libros Disponibles");
            btnConsultarHistorial = new JButton("Consultar Historial de Préstamos");

            panel.add(btnBuscarLibros);
            panel.add(btnConsultarHistorial);

            btnBuscarLibros.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Abre una nueva ventana para buscar libros disponibles
                    new VentanaBuscarLibros(connection);
                }
            });

            btnConsultarHistorial.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Abre una nueva ventana para consultar historial de préstamos
                    new VentanaHistorialPrestamos(connection);
                }
            });

            add(panel);
            setVisible(true);
        }
    }
}
