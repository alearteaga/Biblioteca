        package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public static void insertUsuario(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO Usuarios (Numero, Apellidos, Correo_electronico, Telefono, Rol, Fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario.getNumero());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getCorreoElectronico());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getFechaRegistro());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void updateUsuario(Usuario usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "UPDATE Usuarios SET Numero=?, Apellidos=?, Correo_electronico=?, Telefono=?, Rol=?, Fecha_registro=? WHERE ID_Usuario=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario.getNumero());
            stmt.setString(2, usuario.getApellidos());
            stmt.setString(3, usuario.getCorreoElectronico());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getFechaRegistro());
            stmt.setInt(7, usuario.getId());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void deleteUsuario(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM Usuarios WHERE ID_Usuario=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static List<Usuario> getUsuarios() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Usuarios";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_Usuario");
                String numero = rs.getString("Numero");
                String apellidos = rs.getString("Apellidos");
                String correoElectronico = rs.getString("Correo_electronico");
                String telefono = rs.getString("Telefono");
                String rol = rs.getString("Rol");
                String fechaRegistro = rs.getString("Fecha_registro");

                Usuario usuario = new Usuario(id, numero, apellidos, correoElectronico, telefono, rol, fechaRegistro);
                usuarios.add(usuario);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return usuarios;
    }
}
