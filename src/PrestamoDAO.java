import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private final Connection conexion;

    public PrestamoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para agregar un préstamo a la base de datos
    public void agregarPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO Prestamos (idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, fechaRetornoReal, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, prestamo.getIdLibro());
            pstmt.setInt(2, prestamo.getIdUsuario());
            pstmt.setString(3, prestamo.getFechaPrestamo());
            pstmt.setString(4, prestamo.getFechaRetornoPrevista());
            pstmt.setString(5, prestamo.getFechaRetornoReal());
            pstmt.setString(6, prestamo.getEstado());
            pstmt.executeUpdate();
        }
    }

    // Método para obtener todos los préstamos de la base de datos
    public List<Prestamo> obtenerTodos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idPrestamo = rs.getInt("idPrestamo");
                    int idLibro = rs.getInt("idLibro");
                    int idUsuario = rs.getInt("idUsuario");
                    String fechaPrestamo = rs.getString("fechaPrestamo");
                    String fechaRetornoPrevista = rs.getString("fechaRetornoPrevista");
                    String fechaRetornoReal = rs.getString("fechaRetornoReal");
                    String estado = rs.getString("estado");
                    Prestamo prestamo = new Prestamo(idPrestamo, idLibro, idUsuario, fechaPrestamo, fechaRetornoPrevista, fechaRetornoReal, estado);
                    prestamos.add(prestamo);
                }
            }
        }
        return prestamos;
    }
}
