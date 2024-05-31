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
                // Lógica para añadir un libro
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar un libro
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar un libro
            }
        });
    }

    private List<Libro> getLibros() {
        try {
            return libroDAO.obtenerLibros();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
