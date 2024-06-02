package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    public static void insertPrestamo(Prestamo prestamo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO Prestamos (ID_Libro, ID_Usuario, Fecha_Prestamo, Fecha_Retorno_Prevista, Fecha_Retorno_Real, Estado) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setString(3, prestamo.getFechaPrestamo());
            stmt.setString(4, prestamo.getFechaRetornoPrevista());
            stmt.setString(5, prestamo.getFechaRetornoReal());
            stmt.setString(6, prestamo.getEstado());
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

    public static void updatePrestamo(Prestamo prestamo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "UPDATE Prestamos SET ID_Libro=?, ID_Usuario=?, Fecha_Prestamo=?, Fecha_Retorno_Prevista=?, Fecha_Retorno_Real=?, Estado=? WHERE ID_Préstamo=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, prestamo.getIdLibro());
            stmt.setInt(2, prestamo.getIdUsuario());
            stmt.setString(3, prestamo.getFechaPrestamo());
            stmt.setString(4, prestamo.getFechaRetornoPrevista());
            stmt.setString(5, prestamo.getFechaRetornoReal());
            stmt.setString(6, prestamo.getEstado());
            stmt.setInt(7, prestamo.getId());
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

    public static void deletePrestamo(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM Prestamos WHERE ID_Préstamo=?";
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

    public static List<Prestamo> getPrestamos() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Prestamo> prestamos = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Prestamos";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_Préstamo");
                int idLibro = rs.getInt("ID_Libro");
                int idUsuario = rs.getInt("ID_Usuario");
                String fechaPrestamo = rs.getString("Fecha_Prestamo");
                String fechaRetornoPrevista = rs.getString("Fecha_Retorno_Prevista");
                String fechaRetornoReal = rs.getString("Fecha_Retorno_Real");
                String estado = rs.getString("Estado");

                Prestamo prestamo = new Prestamo(id, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, fechaRetornoReal, estado);
                prestamos.add(prestamo);
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

        return prestamos;
    }
}
