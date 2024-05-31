import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LibrosPanel extends JPanel {
    private final LibroDAO libroDAO;
    private final JTextField tituloField;
    private final JTextField autorField;
    private final JTextField isbnField;
    private final JTextField editorialField;
    private final JTextField anyPublicacionField;
    private final JTextField categoriaField;
    private final JTextField estadoField;

    public LibrosPanel(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        setLayout(new BorderLayout());

        // Formulario de libro
        JPanel formularioPanel = new JPanel(new GridLayout(8, 2));
        formularioPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        formularioPanel.add(tituloField);
        formularioPanel.add(new JLabel("Autor:"));
        autorField = new JTextField();
        formularioPanel.add(autorField);
        formularioPanel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        formularioPanel.add(isbnField);
        formularioPanel.add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        formularioPanel.add(editorialField);
        formularioPanel.add(new JLabel("Año Publicación:"));
        anyPublicacionField = new JTextField();
        formularioPanel.add(anyPublicacionField);
        formularioPanel.add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        formularioPanel.add(categoriaField);
        formularioPanel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        formularioPanel.add(estadoField);

        // Botón de agregar libro
        JButton agregarButton = new JButton("Agregar Libro");
        agregarButton.addActionListener((ActionEvent e) -> {
            agregarLibro();
        });
        formularioPanel.add(agregarButton);

        add(formularioPanel, BorderLayout.NORTH);

        // Tabla de libros
        JTable librosTable = new JTable(new LibrosTableModel(libroDAO));
        add(new JScrollPane(librosTable), BorderLayout.CENTER);
    }

    private void agregarLibro() {
        String titulo = tituloField.getText();
        String autor = autorField.getText();
        String isbn = isbnField.getText();
        String editorial = editorialField.getText();
        int anyPublicacion = Integer.parseInt(anyPublicacionField.getText());
        String categoria = categoriaField.getText();
        String estado = estadoField.getText();

        Libro libro = new Libro(0, titulo, autor, isbn, editorial, anyPublicacion, categoria, estado);

        try {
            libroDAO.agregarLibro(libro);
            JOptionPane.showMessageDialog(this, "Libro agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el libro", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
