package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazPrestamos extends JFrame {
    private PrestamoDAO prestamoDAO;
    private JTextField idField, libroIdField, usuarioIdField, fechaPrestamoField, fechaPrevistaRetornoField, fechaRealRetornoField, estadoField;
    private JTable prestamosTable;

    public InterfazPrestamos(Connection connection) {
        prestamoDAO = new PrestamoDAO(connection);

        setTitle("Gestión de Préstamos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2));

        idField = new JTextField();
        libroIdField = new JTextField();
        usuarioIdField = new JTextField();
        fechaPrestamoField = new JTextField();
        fechaPrevistaRetornoField = new JTextField();
        fechaRealRetornoField = new JTextField();
        estadoField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Libro ID:"));
        formPanel.add(libroIdField);
        formPanel.add(new JLabel("Usuario ID:"));
        formPanel.add(usuarioIdField);
        formPanel.add(new JLabel("Fecha de Préstamo:"));
        formPanel.add(fechaPrestamoField);
        formPanel.add(new JLabel("Fecha Prevista de Retorno:"));
        formPanel.add(fechaPrevistaRetornoField);
        formPanel.add(new JLabel("Fecha Real de Retorno:"));
        formPanel.add(fechaRealRetornoField);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(estadoField);

        // Botones
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (libroIdField.getText().isEmpty() || usuarioIdField.getText().isEmpty() || fechaPrestamoField.getText().isEmpty() ||
                        fechaPrevistaRetornoField.getText().isEmpty() || estadoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Prestamo prestamo = new Prestamo(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(libroIdField.getText()),
                            Integer.parseInt(usuarioIdField.getText()),
                            java.sql.Date.valueOf(fechaPrestamoField.getText()),
                            java.sql.Date.valueOf(fechaPrevistaRetornoField.getText()),
                            fechaRealRetornoField.getText().isEmpty() ? null : java.sql.Date.valueOf(fechaRealRetornoField.getText()),
                            estadoField.getText()
                    );
                    prestamoDAO.agregarPrestamo(prestamo);
                    cargarPrestamos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Modificar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (libroIdField.getText().isEmpty() || usuarioIdField.getText().isEmpty() || fechaPrestamoField.getText().isEmpty() ||
                        fechaPrevistaRetornoField.getText().isEmpty() || estadoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Prestamo prestamo = new Prestamo(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(libroIdField.getText()),
                            Integer.parseInt(usuarioIdField.getText()),
                            java.sql.Date.valueOf(fechaPrestamoField.getText()),
                            java.sql.Date.valueOf(fechaPrevistaRetornoField.getText()),
                            fechaRealRetornoField.getText().isEmpty() ? null : java.sql.Date.valueOf(fechaRealRetornoField.getText()),
                            estadoField.getText()
                    );
                    prestamoDAO.modificarPrestamo(prestamo);
                    cargarPrestamos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    prestamoDAO.eliminarPrestamo(id);
                    cargarPrestamos();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(deleteButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tabla
        prestamosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(prestamosTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        cargarPrestamos();
        setVisible(true);
    }

    private void cargarPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerTodosLosPrestamos();
            prestamosTable.setModel(new PrestamoTableModel(prestamos));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
