import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
                try {
                    new VentanaAgregarLibro(connection).setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnModificarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new VentanaModificarLibro(connection).setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnEliminarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new VentanaEliminarLibro(connection).setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnVerPrestamosActivos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Prestamo> prestamos = PrestamoDAO.getPrestamosActivos(connection);
                    mostrarPrestamos(prestamos);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnVerHistorialPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Prestamo> prestamos = PrestamoDAO.getHistorialPrestamos(connection);
                    mostrarPrestamos(prestamos);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnGestionarMultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new VentanaGestionarMultas(connection).setVisible(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void mostrarPrestamos(List<Prestamo> prestamos) {
        StringBuilder sb = new StringBuilder();
        for (Prestamo prestamo : prestamos) {
            sb.append(prestamo).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Prestamos", JOptionPane.PLAIN_MESSAGE);
    }
}
