import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (nombre, apellidos, email, telefono, rol, fechaRegistro, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            statement.setString(6, usuario.getFechaRegistro());
            statement.setString(7, usuario.getContraseña());
            statement.executeUpdate();
        }
    }

    public Usuario iniciarSesion(String usernameOrEmail, String password) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE (email = ? OR nombre = ?) AND contraseña = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);
            statement.setString(3, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("idUsuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono"),
                        resultSet.getString("rol"),
                        resultSet.getString("fechaRegistro"),
                        resultSet.getString("contraseña")
                );
            } else {
                return null;
            }
        }
    }
    
    public List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                        resultSet.getInt("idUsuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono"),
                        resultSet.getString("rol"),
                        resultSet.getString("fechaRegistro"),
                        resultSet.getString("contraseña")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
