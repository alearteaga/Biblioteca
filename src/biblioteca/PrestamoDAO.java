package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private Connection connection;

    public PrestamoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String query = "INSERT INTO prestamos (libro_id, usuario_id, fecha_prestamo, fecha_prevista_retorno, fecha_real_retorno, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getUsuarioId());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(4, new java.sql.Date(prestamo.getFechaPrevistaRetorno().getTime()));
            stmt.setDate(5, prestamo.getFechaRealRetorno() != null ? new java.sql.Date(prestamo.getFechaRealRetorno().getTime()) : null);
            stmt.setString(6, prestamo.getEstado());
            stmt.executeUpdate();
        }
    }

    public void modificarPrestamo(Prestamo prestamo) throws SQLException {
        String query = "UPDATE prestamos SET libro_id = ?, usuario_id = ?, fecha_prestamo = ?, fecha_prevista_retorno = ?, fecha_real_retorno = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getUsuarioId());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            stmt.setDate(4, new java.sql.Date(prestamo.getFechaPrevistaRetorno().getTime()));
            stmt.setDate(5, prestamo.getFechaRealRetorno() != null ? new java.sql.Date(prestamo.getFechaRealRetorno().getTime()) : null);
            stmt.setString(6, prestamo.getEstado());
            stmt.setInt(7, prestamo.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarPrestamo(int id) throws SQLException {
        String query = "DELETE FROM prestamos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM prestamos";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                prestamos.add(new Prestamo(
                    rs.getInt("id"),
                    rs.getInt("libro_id"),
                    rs.getInt("usuario_id"),
                    rs.getDate("fecha_prestamo"),
                    rs.getDate("fecha_prevista_retorno"),
                    rs.getDate("fecha_real_retorno"),
                    rs.getString("estado")
                ));
            }
        }
        return prestamos;
    }
}
