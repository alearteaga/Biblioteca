import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Connection conexion;

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para agregar un libro a la base de datos
    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libros (titulo, autor, isbn, editorial, anyPublicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getAutor());
            pstmt.setString(3, libro.getIsbn());
            pstmt.setString(4, libro.getEditorial());
            pstmt.setInt(5, libro.getAnyPublicacion());
            pstmt.setString(6, libro.getCategoria());
            pstmt.setString(7, libro.getEstado());
            pstmt.executeUpdate();
        }
    }

    // Método para obtener todos los libros de la base de datos
    public List<Libro> obtenerTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int idLibro = rs.getInt("idLibro");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String isbn = rs.getString("isbn");
                    String editorial = rs.getString("editorial");
                    int anyPublicacion = rs.getInt("anyPublicacion");
                    String categoria = rs.getString("categoria");
                    String estado = rs.getString("estado");
                    Libro libro = new Libro(idLibro, titulo, autor, isbn, editorial, anyPublicacion, categoria, estado);
                    libros.add(libro);
                }
            }
        }
        return libros;
    }
}
