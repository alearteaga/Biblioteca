package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazLector extends JFrame {
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private JTextArea textArea;

    public InterfazLector(Connection connection) {
        this.libroDAO = new LibroDAO(connection);
        this.prestamoDAO = new PrestamoDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);

        setTitle("Interfaz del Lector");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JButton btnConsultarLibros = new JButton("Consultar Libros");
        btnConsultarLibros.addActionListener(e -> consultarLibros());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        panel.add(btnConsultarLibros, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void consultarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerLibrosDisponibles();
            StringBuilder sb = new StringBuilder();
            for (Libro libro : libros) {
                sb.append(libro.toString()).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar los libros: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Aquí deberías crear la conexión a la base de datos
        Connection connection = null;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazLector(connection);
            }
        });
    }
}
