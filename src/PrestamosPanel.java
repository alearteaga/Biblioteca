import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;

public class PrestamosPanel extends JPanel {
    private final PrestamoDAO prestamoDAO;
    private final JTextField idLibroField;
    private final JTextField idUsuarioField;
    private final JTextField fechaPrestamoField;
    private final JTextField fechaRetornoPrevistaField;
    private final JTextField fechaRetornoRealField;
    private final JTextField estadoField;

    public PrestamosPanel(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
        setLayout(new BorderLayout());

        // Formulario de préstamo
        JPanel formularioPanel = new JPanel(new GridLayout(7, 2));
        formularioPanel.add(new JLabel("ID Libro:"));
        idLibroField = new JTextField();
        formularioPanel.add(idLibroField);
        formularioPanel.add(new JLabel("ID Usuario:"));
        idUsuarioField = new JTextField();
        formularioPanel.add(idUsuarioField);
        formularioPanel.add(new JLabel("Fecha Préstamo:"));
        fechaPrestamoField = new JTextField();
        formularioPanel.add(fechaPrestamoField);
        formularioPanel.add(new JLabel("Fecha Retorno Prevista:"));
        fechaRetornoPrevistaField = new JTextField();
        formularioPanel.add(fechaRetornoPrevistaField);
        formularioPanel.add(new JLabel("Fecha Retorno Real:"));
        fechaRetornoRealField = new JTextField();
        formularioPanel.add(fechaRetornoRealField);
        formularioPanel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        formularioPanel.add(estadoField);

        // Botón de agregar préstamo
        JButton agregarButton = new JButton("Agregar Préstamo");
        agregarButton.addActionListener((ActionEvent e) -> {
            agregarPrestamo();
        });
        formularioPanel.add(agregarButton);

        add(formularioPanel, BorderLayout.NORTH);

        // Tabla de préstamos
        JTable prestamosTable = new JTable(new PrestamosTableModel(prestamoDAO));
        add(new JScrollPane(prestamosTable), BorderLayout.CENTER);
    }

    private void agregarPrestamo() {
        int idLibro = Integer.parseInt(idLibroField.getText());
        int idUsuario = Integer.parseInt(idUsuarioField.getText());
        String fechaPrestamo = fechaPrestamoField.getText();
        String fechaRetornoPrevista = fechaRetornoPrevistaField.getText();
        String fechaRetornoReal = fechaRetornoRealField.getText();
        String estado = estadoField.getText();

        Prestamo prestamo = new Prestamo(0, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, fechaRetornoReal, estado);

        try {
            prestamoDAO.agregarPrestamo(prestamo);
            JOptionPane.showMessageDialog(this, "Préstamo agregado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el préstamo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
