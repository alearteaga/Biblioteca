import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class LibrosPanel extends JPanel {
    private final LibroDAO libroDAO;
    private final JTable table;
    private final LibroTableModel tableModel;

    public LibrosPanel(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
        setLayout(new BorderLayout());

        tableModel = new LibroTableModel(getLibros());
        table = new JTable(tableModel);

        JButton addButton = new JButton("Añadir Libro");
        JButton editButton = new JButton("Editar Libro");
        JButton deleteButton = new JButton("Eliminar Libro");

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLibroDialog dialog = new AddLibroDialog();
                dialog.setVisible(true);
                Libro nuevoLibro = dialog.getLibro();
                if (nuevoLibro != null) {
                    try {
                        libroDAO.agregarLibro(nuevoLibro);
                        actualizarTabla();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al agregar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Libro libroSeleccionado = tableModel.getLibros().get(filaSeleccionada);
                    EditLibroDialog dialog = new EditLibroDialog(libroSeleccionado);
                    dialog.setVisible(true);
                    Libro libroEditado = dialog.getLibro();
                    if (libroEditado != null) {
                        try {
                            libroDAO.actualizarLibro(libroEditado);
                            actualizarTabla();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un libro para editar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = table.getSelectedRow();
                if (filaSeleccionada != -1) {
                    Libro libroSeleccionado = tableModel.getLibros().get(filaSeleccionada);
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este libro?");
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            libroDAO.eliminarLibro(libroSeleccionado.getId());
                            actualizarTabla();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un libro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private List<Libro> getLibros() {
        try {
            return libroDAO.obtenerLibros();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los libros: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void actualizarTabla() {
        tableModel.setLibros(getLibros());
        tableModel.fireTableDataChanged();
    }
}
