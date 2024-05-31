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

    public List<Prestamo> obtenerPrestamos() throws SQLException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamos";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        rs.getInt("usuarioId"),
                        rs.getInt("libroId"),
                        rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion")
                );
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
}
