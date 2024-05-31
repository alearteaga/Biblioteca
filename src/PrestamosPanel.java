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
                AddPrestamoDialog dialog = new AddPrestamoDialog();
                dialog.setVisible(true);
                Prestamo nuevoPrestamo = dialog.getPrestamo();
                if (nuevoPrestamo != null) {
                    try {
                        prestamoDAO.agregarPrestamo(nuevoPrestamo);
                        actualizarTabla();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al agregar el préstamo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Prestamo prestamoSeleccionado = tableModel.getPrestamos().get(filaSeleccionada);
                    // Aquí debes implementar la lógica para devolver el préstamo
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un préstamo para devolver", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private List<Prestamo> getPrestamos() {
        try {
            return prestamoDAO.obtenerPrestamos();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los préstamos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

     private void actualizarTabla() {
        tableModel.setPrestamos(getPrestamos());
        tableModel.fireTableDataChanged();
    }
}
       
