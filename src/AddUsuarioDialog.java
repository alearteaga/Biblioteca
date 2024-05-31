import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddUsuarioDialog extends JDialog {
    private JTextField nombreField, apellidosField, emailField, telefonoField, rolField;
    private Usuario usuario;

    public AddUsuarioDialog() {
        setTitle("Añadir Usuario");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Apellidos:"));
        apellidosField = new JTextField();
        add(apellidosField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("Rol:"));
        rolField = new JTextField();
        add(rolField);

        JButton addButton = new JButton("Añadir");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = new Usuario(
                        0, // ID will be assigned by the database
                        nombreField.getText(),
                        apellidosField.getText(),
                        emailField.getText(),
                        telefonoField.getText(),
                        rolField.getText(),
                        new Date() // Fecha de Registro will be the current date
                );
                dispose();
            }
        });
        add(addButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = null;
                dispose();
            }
        });
        add(cancelButton);
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
