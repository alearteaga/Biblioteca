package biblioteca.dialogs;

import biblioteca.Libro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibroDialog extends JDialog {
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtIsbn;
    private JTextField txtEditorial;
    private JTextField txtAnoPublicacion;
    private JTextField txtCategoria;
    private JTextField txtEstado;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private Libro libro;

    public LibroDialog(Frame owner, Libro libro) {
        super(owner, true);
        this.libro = libro;
        initUI();
        setModal(true);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        setTitle(libro == null ? "Agregar Libro" : "Modificar Libro");
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        panel.add(txtTitulo);

        panel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        panel.add(txtAutor);

        panel.add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        panel.add(txtIsbn);

        panel.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        panel.add(txtEditorial);

        panel.add(new JLabel("Año de Publicación:"));
        txtAnoPublicacion = new JTextField();
        panel.add(txtAnoPublicacion);

        panel.add(new JLabel("Categoría:"));
        txtCategoria = new JTextField();
        panel.add(txtCategoria);

        panel.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panel.add(txtEstado);

        add(panel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        btnPanel.add(btnGuardar);
        btnPanel.add(btnCancelar);

        add(btnPanel, BorderLayout.SOUTH);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLibro();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        if (libro != null) {
            txtTitulo.setText(libro.getTitulo());
            txtAutor.setText(libro.getAutor());
            txtIsbn.setText(libro.getIsbn());
            txtEditorial.setText(libro.getEditorial());
            txtAnoPublicacion.setText(String.valueOf(libro.getAñoPublicacion()));
            txtCategoria.setText(libro.getCategoria());
            txtEstado.setText(libro.getEstado());
        }
    }

    private void guardarLibro() {
        if (libro == null) {
            libro = new Libro();
        }

        libro.setTitulo(txtTitulo.getText());
        libro.setAutor(txtAutor.getText());
        libro.setIsbn(txtIsbn.getText());
        libro.setEditorial(txtEditorial.getText());
        libro.setAñoPublicacion(Integer.parseInt(txtAnoPublicacion.getText()));
        libro.setCategoria(txtCategoria.getText());
        libro.setEstado(txtEstado.getText());

        // Aquí puedes agregar la lógica para guardar el libro en la base de datos
        // usando tu clase LibroDAO

        dispose();
    }

    public Libro getLibro() {
        return libro;
    }
}
