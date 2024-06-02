import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InterfazBibliotecario extends JFrame {
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

        btnModificarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para modificar un libro
                new VentanaModificarLibro(connection);
            }
        });

        btnEliminarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para eliminar un libro
                new VentanaEliminarLibro(connection);
            }
        });

        btnVerPrestamosActivos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para ver préstamos activos
                new VentanaPrestamosActivos(connection);
            }
        });

        btnVerHistorialPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para ver historial de préstamos
                new VentanaHistorialPrestamos(connection);
            }
        });

        btnGestionarMultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para gestionar multas
                new VentanaGestionarMultas(connection);
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "usuario", "contraseña");
            InterfazBibliotecario interfazBibliotecario = new InterfazBibliotecario(connection);
            InterfazLector interfazLector = new InterfazLector(connection);
            interfazBibliotecario.setVisible(true);
            interfazLector.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
