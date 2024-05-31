import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUsuarioDialog extends JDialog {
    private JTextField nombreField;
    private JTextField emailField;
    private JTextField telefonoField;
    private JButton okButton;
    private JButton cancelButton;
    private Usuario usuario;

    public AddUsuarioDialog() {
        setTitle("Añadir Usuario");
        initUI();
    }

    public AddUsuarioDialog(Usuario usuario) {
        setTitle("Editar Usuario");
        initUI();
        setFields(usuario);
    }

    private void initUI() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        add(okButton);
        add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    usuario = new Usuario();
                    usuario.setNombre(nombreField.getText());
                    usuario.setEmail(emailField.getText());
                    usuario.setTelefono(telefonoField.getText());
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = null;
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setModal(true);
    }

    private boolean validateFields() {
        if (nombreField.getText().isEmpty() || emailField.getText().isEmpty() || telefonoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void setFields(Usuario usuario) {
        nombreField.setText(usuario.getNombre());
        emailField.setText(usuario.getEmail());
        telefonoField.setText(usuario.getTelefono());
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
