import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddPrestamoDialog extends JDialog {
    private JComboBox<Usuario> usuarioComboBox;
    private JComboBox<Libro> libroComboBox;
    private JTextField fechaPrestamoField;
    private JTextField fechaDevolucionField;
    private JButton okButton;
    private JButton cancelButton;
    private Prestamo prestamo;

    public AddPrestamoDialog(List<Usuario> usuarios, List<Libro> libros) {
        setTitle("Añadir Préstamo");
        initUI(usuarios, libros);
    }

    private void initUI(List<Usuario> usuarios, List<Libro> libros) {
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Usuario:"));
        usuarioComboBox = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        add(usuarioComboBox);

        add(new JLabel("Libro:"));
        libroComboBox = new JComboBox<>(libros.toArray(new Libro[0]));
        add(libroComboBox);

        add(new JLabel("Fecha de Préstamo:"));
        fechaPrestamoField = new JTextField();
        add(fechaPrestamoField);

        add(new JLabel("Fecha de Devolución:"));
        fechaDevolucionField = new JTextField();
        add(fechaDevolucionField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    prestamo = new Prestamo();
                    prestamo.setUsuario((Usuario) usuarioComboBox.getSelectedItem());
                    prestamo.setLibro((Libro) libroComboBox.getSelectedItem());
                    prestamo.setFechaPrestamo(fechaPrestamoField.getText());
                    prestamo.setFechaDevolucion(fechaDevolucionField.getText());
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamo = null;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setModal(true);
    }

    private boolean validateFields() {
        if (fechaPrestamoField.getText().isEmpty() || fechaDevolucionField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
}
