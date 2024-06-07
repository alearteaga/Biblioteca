package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private Connection conexion;

    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO Prestamos (id_libro, id_usuario, fecha_prestamo, fecha_retorno_prevista, fecha_retorno_real, estado, multa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setDate(3, prestamo.getFechaPrestamo());
            stmt.setDate(4, prestamo.getFechaRetornoPrevista());
            stmt.setDate(5, prestamo.getFechaRetornoReal());
            stmt.setString(6, prestamo.getEstado());
            stmt.setDouble(7, prestamo.getMulta());
            stmt.executeUpdate();
        }
    }

    public void eliminarPrestamo(int id) throws SQLException {
        String sql = "DELETE FROM Prestamos WHERE id_prestamo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void modificarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE Prestamos SET id_libro = ?, id_usuario = ?, fecha_prestamo = ?, fecha_retorno_prevista = ?, fecha_retorno_real = ?, estado = ?, multa = ? WHERE id_prestamo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setDate(3, prestamo.getFechaPrestamo());
            stmt.setDate(4, prestamo.getFechaRetornoPrevista());
            stmt.setDate(5, prestamo.getFechaRetornoReal());
            stmt.setString(6, prestamo.getEstado());
            stmt.setDouble(7, prestamo.getMulta());
            stmt.setInt(8, prestamo.getIdPrestamo());
            stmt.executeUpdate();
        }
    }

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                prestamo.setIdLibro(rs.getInt("id_libro"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
                prestamo.setFechaRetornoPrevista(rs.getDate("fecha_retorno_prevista"));
                prestamo.setFechaRetornoReal(rs.getDate("fecha_retorno_real"));
                prestamo.setEstado(rs.getString("estado"));
                prestamo.setMulta(rs.getDouble("multa"));
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }

    public Prestamo obtenerPrestamoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Prestamos WHERE id_prestamo = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamo(rs.getInt("id_prestamo"));
                    prestamo.setIdLibro(rs.getInt("id_libro"));
                    prestamo.setIdUsuario(rs.getInt("id_usuario"));
                    prestamo.setFechaPrestamo(rs.getDate("fecha_prestamo"));
                    prestamo.setFechaRetornoPrevista(rs.getDate("fecha_retorno_prevista"));
                    prestamo.setFechaRetornoReal(rs.getDate("fecha_retorno_real"));
                    prestamo.setEstado(rs.getString("estado"));
                    prestamo.setMulta(rs.getDouble("multa"));
                    return prestamo;
                } else {
                    return null;
                }
            }
        }
    }
}
