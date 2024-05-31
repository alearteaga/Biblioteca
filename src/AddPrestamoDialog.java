import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPrestamoDialog extends JDialog {
    private JTextField usuarioIdField, libroIdField, fechaPrestamoField, fechaRetornoPrevistaField, estadoField;
    private Prestamo prestamo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AddPrestamoDialog() {
        setTitle("Añadir Préstamo");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("ID Usuario:"));
        usuarioIdField = new JTextField();
        add(usuarioIdField);

        add(new JLabel("ID Libro:"));
        libroIdField = new JTextField();
        add(libroIdField);

        add(new JLabel("Fecha de Préstamo (yyyy-MM-dd):"));
        fechaPrestamoField = new JTextField();
        add(fechaPrestamoField);

        add(new JLabel("Fecha de Retorno Prevista (yyyy-MM-dd):"));
        fechaRetornoPrevistaField = new JTextField();
        add(fechaRetornoPrevistaField);

        add(new JLabel("Estado:"));
        estadoField = new JTextField();
        add(estadoField);

        JButton addButton = new JButton("Añadir");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prestamo = new Prestamo(
                            0, // ID will be assigned by the database
                            Integer.parseInt(usuarioIdField.getText()),
                            Integer.parseInt(libroIdField.getText()),
                            dateFormat.parse(fechaPrestamoField.getText()),
                            dateFormat.parse(fechaRetornoPrevistaField.getText()),
                            null, // Fecha de Retorno Real will be null initially
                            estadoField.getText()
                    );
                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de la fecha: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamo = null;
                dispose();
            }
        });
        add(cancelButton);
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }
}
