import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PrestamosPanel extends JPanel {
    private final PrestamoDAO prestamoDAO;
    private JTable prestamosTable;

    public PrestamosPanel(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        setLayout(new BorderLayout());

        try {
            PrestamoTableModel prestamoTableModel = new PrestamoTableModel(prestamoDAO.obtenerPrestamos());
            prestamosTable = new JTable(prestamoTableModel);
            add(new JScrollPane(prestamosTable), BorderLayout.CENTER);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de préstamos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
