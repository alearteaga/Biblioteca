package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private Connection connection;

    public PrestamoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String query = "INSERT INTO prestamos (id_prestamo, id_libro, id_usuario, fecha_prestamo, fecha_retorno_prevista, fecha_retorno_real, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prestamo.getIdPrestamo());
            statement.setInt(2, prestamo.getIdLibro());
            statement.setInt(3, prestamo.getIdUsuario());
            statement.setDate(4, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            statement.setDate(5, new java.sql.Date(prestamo.getFechaRetornoPrevista().getTime()));
            if (prestamo.getFechaRetornoReal() != null) {
                statement.setDate(6, new java.sql.Date(prestamo.getFechaRetornoReal().getTime()));
            } else {
                statement.setDate(6, null);
            }
            statement.setString(7, prestamo.getEstado());
            statement.executeUpdate();
        }
    }

    public void actualizarPrestamo(Prestamo prestamo) throws SQLException {
        String query = "UPDATE prestamos SET fecha_retorno_real = ?, estado = ? WHERE id_prestamo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (prestamo.getFechaRetornoReal() != null) {
                statement.setDate(1, new java.sql.Date(prestamo.getFechaRetornoReal().getTime()));
            } else {
                statement.setDate(1, null);
            }
            statement.setString(2, prestamo.getEstado());
            statement.setInt(3, prestamo.getIdPrestamo());
            statement.executeUpdate();
        }
    }

    public void eliminarPrestamo(int idPrestamo) throws SQLException {
        String query = "DELETE FROM prestamos WHERE id_prestamo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPrestamo);
            statement.executeUpdate();
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM prestamos";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    prestamos.add(new Prestamo(
                            resultSet.getInt("id_prestamo"),
                            resultSet.getInt("id_libro"),
                            resultSet.getInt("id_usuario"),
                            resultSet.getDate("fecha_prestamo"),
                            resultSet.getDate("fecha_retorno_prevista"),
                            resultSet.getDate("fecha_retorno_real"),
                            resultSet.getString("estado")
                    ));
                }
            }
        }
        return prestamos;
    }

    public Prestamo obtenerPrestamoPorId(int idPrestamo) throws SQLException {
        String query = "SELECT * FROM prestamos WHERE id_prestamo = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPrestamo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idLibro = resultSet.getInt("id_libro");
                    int idUsuario = resultSet.getInt("id_usuario");
                    java.util.Date fechaPrestamo = resultSet.getDate("fecha_prestamo");
                    java.util.Date fechaRetornoPrevista = resultSet.getDate("fecha_retorno_prevista");
                    java.util.Date fechaRetornoReal = resultSet.getDate("fecha_retorno_real");
                    String estado = resultSet.getString("estado");
                    return new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, fechaRetornoReal, estado);
                } else {
                    return null;
                }
            }
        }
    }
}
