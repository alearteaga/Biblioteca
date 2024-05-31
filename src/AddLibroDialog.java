import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLibroDialog extends JDialog {
    private JTextField tituloField, autorField, isbnField, editorialField, anoPublicacionField, categoriaField, estadoField;
    private Libro libro;

    public AddLibroDialog() {
        setTitle("Añadir Libro");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Título:"));
        tituloField = new JTextField();
        add(tituloField);

        add(new JLabel("Autor:"));
        autorField = new JTextField();
        add(autorField);

        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        add(new JLabel("Editorial:"));
        editorialField = new JTextField();
        add(editorialField);

        add(new JLabel("Año de Publicación:"));
        anoPublicacionField = new JTextField();
        add(anoPublicacionField);

        add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        add(categoriaField);

        add(new JLabel("Estado:"));
        estadoField = new JTextField();
        add(estadoField);

        JButton addButton = new JButton("Añadir");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libro = new Libro(
                        0, // ID will be assigned by the database
                        tituloField.getText(),
                        autorField.getText(),
                        isbnField.getText(),
                        editorialField.getText(),
                        Integer.parseInt(anoPublicacionField.getText()),
                        categoriaField.getText(),
                        estadoField.getText()
                );
                dispose();
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libro = null;
                dispose();
            }
        });
        add(cancelButton);
    }

    public Libro getLibro() {
        return libro;
    }
}
