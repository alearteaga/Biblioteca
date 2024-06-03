package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazPrestamos extends JFrame {
    private PrestamoDAO prestamoDAO;
    private JTextField idField, libroIdField, usuarioIdField, fechaPrestamoField, fechaPrevistaField, fechaRealField, estadoField;
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
        fechaPrevistaField = new JTextField();
        fechaRealField = new JTextField();
        estadoField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("ID del Libro:"));
        formPanel.add(libroIdField);
        formPanel.add(new JLabel("ID del Usuario:"));
        formPanel.add(usuarioIdField);
        formPanel.add(new JLabel("Fecha de Préstamo:"));
        formPanel.add(fechaPrestamoField);
        formPanel.add(new JLabel("Fecha Prevista de Retorno:"));
        formPanel.add(fechaPrevistaField);
        formPanel.add(new JLabel("Fecha Real de Retorno:"));
        formPanel.add(fechaRealField);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(estadoField);

        // Botones
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Prestamo prestamo = new Prestamo(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(libroIdField.getText()),
                            Integer.parseInt(usuarioIdField.getText()),
                            new java.util.Date(fechaPrestamoField.getText()),
                            new java.util.Date(fechaPrevistaField.getText()),
                            new java.util.Date(fechaRealField.getText()),
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
                try {
                    Prestamo prestamo = new Prestamo(
                            Integer.parseInt(idField.getText()),
                            Integer.parseInt(libroIdField.getText()),
                            Integer.parseInt(usuarioIdField.getText()),
                            new java.util.Date(fechaPrestamoField.getText()),
                            new java.util.Date(fechaPrevistaField.getText()),
                            new java.util.Date(fechaRealField.getText()),
                            estadoField.getText()
                    );
                    prestamoDAO.actualizarPrestamo(prestamo);
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
