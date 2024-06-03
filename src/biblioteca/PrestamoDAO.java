package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
