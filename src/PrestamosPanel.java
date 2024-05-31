import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PrestamosPanel extends JPanel {
    private final PrestamoDAO prestamoDAO;
    private final JTable table;
    private final PrestamoTableModel tableModel;

    public PrestamosPanel(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        setLayout(new BorderLayout());

        tableModel = new PrestamoTableModel(getPrestamos());
        table = new JTable(tableModel);

        JButton addButton = new JButton("Añadir Préstamo");
        JButton returnButton = new JButton("Devolver Préstamo");

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(returnButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para añadir un préstamo
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para devolver un préstamo
            }
        });
    }

    private List<Prestamo> getPrestamos() {
        try {
            return prestamoDAO.obtenerPrestamos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
