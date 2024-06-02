import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazLector extends JFrame {
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
                try {
                    List<Libro> libros = LibroDAO.obtenerLibrosDisponibles(connection);
                    mostrarLibros(libros);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnConsultarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID de usuario:"));
                    List<Prestamo> historial = PrestamoDAO.getHistorialPrestamosUsuario(connection, idUsuario);
                    mostrarHistorial(historial);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void mostrarLibros(List<Libro> libros) {
        StringBuilder sb = new StringBuilder();
        for (Libro libro : libros) {
            sb.append(libro).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Libros Disponibles", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarHistorial(List<Prestamo> historial) {
        StringBuilder sb = new StringBuilder();
        for (Prestamo prestamo : historial) {
            sb.append(prestamo).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Historial de Préstamos", JOptionPane.PLAIN_MESSAGE);
    }
}
