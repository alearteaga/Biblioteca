import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUsuarioDialog extends JDialog {
    private JTextField nombreField, apellidosField, emailField, telefonoField, rolField;
    private JButton updateButton, cancelButton;
    private Usuario usuario;

    public EditUsuarioDialog(Usuario usuarioSeleccionado) {
        setTitle("Editar Usuario");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        // Inicializar campos con los valores actuales del usuario seleccionado
        nombreField = new JTextField(usuarioSeleccionado.getNombre());
        add(new JLabel("Nombre:"));
        add(nombreField);

        apellidosField = new JTextField(usuarioSeleccionado.getApellidos());
        add(new JLabel("Apellidos:"));
        add(apellidosField);

        emailField = new JTextField(usuarioSeleccionado.getEmail());
        add(new JLabel("Email:"));
        add(emailField);

        telefonoField = new JTextField(usuarioSeleccionado.getTelefono());
        add(new JLabel("Teléfono:"));
        add(telefonoField);

        rolField = new JTextField(usuarioSeleccionado.getRol());
        add(new JLabel("Rol:"));
        add(rolField);

        updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualizar los datos del usuario con los valores ingresados
                usuario = new Usuario(
                        usuarioSeleccionado.getId(),
                        nombreField.getText(),
                        apellidosField.getText(),
                        emailField.getText(),
                        telefonoField.getText(),
                        rolField.getText(),
                        usuarioSeleccionado.getFechaRegistro()
                );
                dispose();
            }
        });
        add(updateButton);

        cancelButton = new JButton("Cancelar");
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
