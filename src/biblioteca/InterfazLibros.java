package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class InterfazLibros extends JFrame {
    private LibroDAO libroDAO;
    private JTextField idField, tituloField, autorField, isbnField, editorialField, anioField, categoriaField, estadoField;
    private JTable librosTable;

    public InterfazLibros(Connection connection) {
        libroDAO = new LibroDAO(connection);

        setTitle("Gestión de Libros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2));

        idField = new JTextField();
        tituloField = new JTextField();
        autorField = new JTextField();
        isbnField = new JTextField();
        editorialField = new JTextField();
        anioField = new JTextField();
        categoriaField = new JTextField();
        estadoField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Título:"));
        formPanel.add(tituloField);
        formPanel.add(new JLabel("Autor:"));
        formPanel.add(autorField);
        formPanel.add(new JLabel("ISBN:"));
        formPanel.add(isbnField);
        formPanel.add(new JLabel("Editorial:"));
        formPanel.add(editorialField);
        formPanel.add(new JLabel("Año de Publicación:"));
        formPanel.add(anioField);
        formPanel.add(new JLabel("Categoría:"));
        formPanel.add(categoriaField);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(estadoField);

        // Botones
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Libro libro = new Libro(
                            Integer.parseInt(idField.getText()),
                            tituloField.getText(),
                            autorField.getText(),
                            isbnField.getText(),
                            editorialField.getText(),
                            Integer.parseInt(anioField.getText()),
                            categoriaField.getText(),
                            estadoField.getText()
                    );
                    libroDAO.agregarLibro(libro);
                    cargarLibros();
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
                    Libro libro = new Libro(
                            Integer.parseInt(idField.getText()),
                            tituloField.getText(),
                            autorField.getText(),
                            isbnField.getText(),
                            editorialField.getText(),
                            Integer.parseInt(anioField.getText()),
                            categoriaField.getText(),
                            estadoField.getText()
                    );
                    libroDAO.modificarLibro(libro);
                    cargarLibros();
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
                    libroDAO.eliminarLibro(id);
                    cargarLibros();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(deleteButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tabla
        librosTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(librosTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        cargarLibros();
        setVisible(true);
    }

    private void cargarLibros() {
        try {
            List<Libro> libros = libroDAO.obtenerTodosLosLibros();
            librosTable.setModel(new LibroTableModel(libros));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
