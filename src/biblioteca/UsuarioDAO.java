package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (id_usuario, numero, apellidos, correo_electronico, telefono, rol, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuario.getIdUsuario());
            statement.setInt(2, usuario.getNumero());
            statement.setString(3, usuario.getApellidos());
            statement.setString(4, usuario.getCorreoElectronico());
            statement.setString(5, usuario.getTelefono());
            statement.setString(6, usuario.getRol());
            statement.setDate(7, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            statement.executeUpdate();
        }
    }

    public void modificarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios SET numero = ?, apellidos = ?, correo_electronico = ?, telefono = ?, rol = ?, fecha_registro = ? WHERE id_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuario.getNumero());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getCorreoElectronico());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            statement.setDate(6, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            statement.setInt(7, usuario.getIdUsuario());
            statement.executeUpdate();
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        }
    }
}
