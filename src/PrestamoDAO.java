import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private Connection connection;

    public PrestamoDAO() {
        try {
            // Aquí debes inicializar tu conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:tu_url_de_conexion", "usuario", "contraseña");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo, fecha_retorno_prevista, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getUsuarioId());
            statement.setInt(2, prestamo.getLibroId());
            statement.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            statement.setDate(4, new java.sql.Date(prestamo.getFechaRetornoPrevista().getTime()));
            statement.setString(5, prestamo.getEstado());
            statement.executeUpdate();
        }
    }

    public void actualizarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE prestamos SET usuario_id = ?, libro_id = ?, fecha_prestamo = ?, fecha_retorno_prevista = ?, fecha_retorno_real = ?, estado = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prestamo.getUsuarioId());
            statement.setInt(2, prestamo.getLibroId());
            statement.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            statement.setDate(4, new java.sql.Date(prestamo.getFechaRetornoPrevista().getTime()));
            statement.setDate(5, prestamo.getFechaRetornoReal() != null ? new java.sql.Date(prestamo.getFechaRetornoReal().getTime()) : null);
            statement.setString(6, prestamo.getEstado());
            statement.setInt(7, prestamo.getId());
            statement.executeUpdate();
        }
    }

    public void eliminarPrestamo(int id) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public List<Prestamo> obtenerPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                prestamos.add(new Prestamo(
                        resultSet.getInt("id"),
                        resultSet.getInt("usuario_id"),
                        resultSet.getInt("libro_id"),
                        resultSet.getDate("fecha_prestamo"),
                        resultSet.getDate("fecha_retorno_prevista"),
                        resultSet.getDate("fecha_retorno_real"),
                        resultSet.getString("estado")
                ));
            }
        }
        return prestamos;
    }
}
