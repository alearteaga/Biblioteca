import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditLibroDialog extends JDialog {
    private JTextField tituloField, autorField, isbnField, editorialField, anoPublicacionField, categoriaField, estadoField;
    private JButton updateButton, cancelButton;
    private Libro libro;

    public EditLibroDialog(Libro libroSeleccionado) {
        setTitle("Editar Libro");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));

        // Inicializar campos con los valores actuales del libro seleccionado
        tituloField = new JTextField(libroSeleccionado.getTitulo());
        add(new JLabel("Título:"));
        add(tituloField);

        autorField = new JTextField(libroSeleccionado.getAutor());
        add(new JLabel("Autor:"));
        add(autorField);

        isbnField = new JTextField(libroSeleccionado.getIsbn());
        add(new JLabel("ISBN:"));
        add(isbnField);

        editorialField = new JTextField(libroSeleccionado.getEditorial());
        add(new JLabel("Editorial:"));
        add(editorialField);

        anoPublicacionField = new JTextField(Integer.toString(libroSeleccionado.getAnoPublicacion()));
        add(new JLabel("Año de Publicación:"));
        add(anoPublicacionField);

        categoriaField = new JTextField(libroSeleccionado.getCategoria());
        add(new JLabel("Categoría:"));
        add(categoriaField);

        estadoField = new JTextField(libroSeleccionado.getEstado());
        add(new JLabel("Estado:"));
        add(estadoField);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar los datos del libro con los valores ingresados
                libro = new Libro(
                        libroSeleccionado.getId(),
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
        add(updateButton);

        cancelButton = new JButton("Cancelar");
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
