package biblioteca.dialogs;

import biblioteca.Usuario;
import biblioteca.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class UsuarioDialog extends JDialog {
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtContrasena;
    private JTextField txtRol;
    private JTextField txtFechaRegistro;
    private JButton btnGuardar;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;

    public UsuarioDialog(Frame parent, UsuarioDAO usuarioDAO, Usuario usuario) {
        super(parent, true);
        this.usuarioDAO = usuarioDAO;
        this.usuario = usuario;
        setTitle(usuario == null ? "Agregar Usuario" : "Modificar Usuario");
        initComponents();
        if (usuario != null) {
            cargarDatosUsuario();
        }
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);
        panel.add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        panel.add(txtApellidos);
        panel.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panel.add(txtCorreo);
        panel.add(new JLabel("Telefono:"));
        txtTelefono = new JTextField();
        panel.add(txtTelefono);
        panel.add(new JLabel("Contraseña:"));
        txtContrasena = new JTextField();
        panel.add(txtContrasena);
        panel.add(new JLabel("Rol:"));
        txtRol = new JTextField();
        panel.add(txtRol);
        panel.add(new JLabel("Fecha Registro (yyyy-mm-dd):"));
        txtFechaRegistro = new JTextField();
        panel.add(txtFechaRegistro);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void cargarDatosUsuario() {
        txtNombre.setText(usuario.getNombre());
        txtApellidos.setText(usuario.getApellidos());
        txtCorreo.setText(usuario.getCorreo());
        txtTelefono.setText(usuario.getTelefono());
        txtContrasena.setText(usuario.getContrasena());
        txtRol.setText(usuario.getRol());
        txtFechaRegistro.setText(usuario.getFechaRegistro().toString());
    }

    private void guardarUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario.setNombre(txtNombre.getText());
        usuario.setApellidos(txtApellidos.getText());
        usuario.setCorreo(txtCorreo.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setContrasena(txtContrasena.getText());
        usuario.setRol(txtRol.getText());
        usuario.setFechaRegistro(Date.valueOf(txtFechaRegistro.getText()));

        try {
            if (usuario.getIdUsuario() == 0) {
                usuarioDAO.agregarUsuario(usuario);
            } else {
                usuarioDAO.modificarUsuario(usuario);
            }
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
