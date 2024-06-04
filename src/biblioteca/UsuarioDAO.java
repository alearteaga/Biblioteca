package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        String query = "INSERT INTO usuarios (numero, apellidos, correo, telefono, rol, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getNumero());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getRol());
            stmt.setDate(6, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.executeUpdate();
        }
    }

    public void modificarUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuarios SET numero = ?, apellidos = ?, correo = ?, telefono = ?, rol = ?, fecha_registro = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usuario.getNumero());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getRol());
            stmt.setDate(6, new java.sql.Date(usuario.getFechaRegistro().getTime()));
            stmt.setInt(7, usuario.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuarios";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getInt("id"),
                    rs.getInt("numero"),
                    rs.getString("apellidos"),
                    rs.getString("correo"),
                    rs.getString("telefono"),
                    rs.getString("rol"),
                    rs.getDate("fecha_registro")
                ));
            }
        }
        return usuarios;
    }
}
