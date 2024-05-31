import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO {
    private final Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, apellidos, email, telefono, rol, fecha_registro) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellidos());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefono());
            statement.setString(5, usuario.getRol());
            statement.setString(6, usuario.getFechaRegistro());
            statement.executeUpdate();
        }
    }

    public Usuario iniciarSesion(String email, String password) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono"),
                        resultSet.getString("rol"),
                        resultSet.getString("fecha_registro")
                );
            } else {
                return null;
            }
        }
    }
    
    public List<Usuario> obtenerTodos() throws SQLException {
        return null;
        // Método para obtener todos los usuarios
    }
}
