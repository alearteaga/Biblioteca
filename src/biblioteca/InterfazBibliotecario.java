package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class InterfazBibliotecario extends JFrame {
    private LibroDAO libroDAO;
    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;
    private JTextField txtIdLibro;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtAnioPublicacion;
    private JTextField txtCategoria;
    private JTextField txtEstado;

    public InterfazBibliotecario(Connection connection) {
        this.libroDAO = new LibroDAO(connection);
        this.prestamoDAO = new PrestamoDAO(connection);
        this.usuarioDAO = new UsuarioDAO(connection);

        setTitle("Interfaz del Bibliotecario");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        panel.add(new JLabel("ID del Libro:"));
        txtIdLibro = new JTextField();
        panel.add(txtIdLibro);

        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        panel.add(txtIsbn);

        panel.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        panel.add(txtEditorial);

        panel.add(new JLabel("Año de Publicación:"));
        txtAnioPublicacion = new JTextField();
        panel.add(txtAnioPublicacion);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        panel.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panel.add(txtEstado);

        JButton btnAgregarLibro = new JButton("Agregar Libro");
        btnAgregarLibro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
            }
        });
        panel.add(btnAgregarLibro);

        add(panel);
        setVisible(true);
    }

    private void agregarLibro() {
        int idLibro = Integer.parseInt(txtIdLibro.getText());
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String isbn = txtIsbn.getText();
        String editorial = txtEditorial.getText();
        int añoPublicacion = Integer.parseInt(txtAnioPublicacion.getText());
        String categoria = txtCategoria.getText();
        String estado = txtEstado.getText();

        Libro libro = new Libro(idLibro, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
        try {
            libroDAO.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Aquí deberías crear la conexión a la base de datos
        Connection connection = null;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazBibliotecario(connection);
            }
        });
    }
}
