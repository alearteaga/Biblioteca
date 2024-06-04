package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Connection connection;

    public LibroDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO libros (titulo, autor, isbn, editorial, anio_publicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnioPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
            stmt.executeUpdate();
        }
    }

    public void modificarLibro(Libro libro) throws SQLException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, editorial = ?, anio_publicacion = ?, categoria = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAnioPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
            stmt.setInt(8, libro.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarLibro(int id) throws SQLException {
        String query = "DELETE FROM libros WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM libros";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                libros.add(new Libro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("isbn"),
                    rs.getString("editorial"),
                    rs.getInt("anio_publicacion"),
                    rs.getString("categoria"),
                    rs.getString("estado")
                ));
            }
        }
        return libros;
    }
}
