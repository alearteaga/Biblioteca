import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LibrosPanel extends JPanel {
    private final LibroDAO libroDAO;
    private JTable librosTable;

    public LibrosPanel(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        setLayout(new BorderLayout());

        try {
            LibroTableModel libroTableModel = new LibroTableModel(libroDAO.obtenerLibros());
            librosTable = new JTable(libroTableModel);
            add(new JScrollPane(librosTable), BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
