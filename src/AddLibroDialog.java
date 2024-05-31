import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLibroDialog extends JDialog {
    private JTextField tituloField;
    private JTextField autorField;
    private JTextField isbnField;
    private JTextField editorialField;
    private JTextField anoPublicacionField;
    private JTextField categoriaField;
    private JComboBox<String> estadoComboBox;
    private JButton okButton;
    private JButton cancelButton;
    private Libro libro;

    public AddLibroDialog() {
        setTitle("Añadir Libro");
        initUI();
    }

    public AddLibroDialog(Libro libro) {
        setTitle("Editar Libro");
        initUI();
        setFields(libro);
    }

    private void initUI() {
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

        add(new JLabel("Año Publicación:"));
        anoPublicacionField = new JTextField();
        add(anoPublicacionField);

        add(new JLabel("Categoría:"));
        categoriaField = new JTextField();
        add(categoriaField);

        add(new JLabel("Estado:"));
        estadoComboBox = new JComboBox<>(new String[]{"Disponible", "Prestado", "En mantenimiento"});
        add(estadoComboBox);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    libro = new Libro();
                    libro.setTitulo(tituloField.getText());
                    libro.setAutor(autorField.getText());
                    libro.setIsbn(isbnField.getText());
                    libro.setEditorial(editorialField.getText());
                    libro.setAnoPublicacion(Integer.parseInt(anoPublicacionField.getText()));
                    libro.setCategoria(categoriaField.getText());
                    libro.setEstado((String) estadoComboBox.getSelectedItem());
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                libro = null;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setModal(true);
    }

    private boolean validateFields() {
        if (tituloField.getText().isEmpty() || autorField.getText().isEmpty() || isbnField.getText().isEmpty() || 
            editorialField.getText().isEmpty() || anoPublicacionField.getText().isEmpty() || 
            categoriaField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(anoPublicacionField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año de publicación debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void setFields(Libro libro) {
        tituloField.setText(libro.getTitulo());
        autorField.setText(libro.getAutor());
        isbnField.setText(libro.getIsbn());
        editorialField.setText(libro.getEditorial());
        anoPublicacionField.setText(String.valueOf(libro.getAnoPublicacion()));
        categoriaField.setText(libro.getCategoria());
        estadoComboBox.setSelectedItem(libro.getEstado());
    }

    public Libro getLibro() {
        return libro;
    }
}
