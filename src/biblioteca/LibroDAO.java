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
        String query = "INSERT INTO libros (id_libro, titulo, autor, isbn, editorial, año_publicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, libro.getIdLibro());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setString(4, libro.getIsbn());
            statement.setString(5, libro.getEditorial());
            statement.setInt(6, libro.getAñoPublicacion());
            statement.setString(7, libro.getCategoria());
            statement.setString(8, libro.getEstado());
            statement.executeUpdate();
        }
    }

    public List<Libro> obtenerLibrosDisponibles() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM libros WHERE estado = 'disponible'";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idLibro = resultSet.getInt("id_libro");
                String titulo = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String isbn = resultSet.getString("isbn");
                String editorial = resultSet.getString("editorial");
                int añoPublicacion = resultSet.getInt("año_publicacion");
                String categoria = resultSet.getString("categoria");
                String estado = resultSet.getString("estado");
                libros.add(new Libro(idLibro, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado));
            }
        }
        return libros;
    }

    public void modificarLibro(Libro libro) throws SQLException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, editorial = ?, año_publicacion = ?, categoria = ?, estado = ? WHERE id_libro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setString(4, libro.getEditorial());
            statement.setInt(5, libro.getAñoPublicacion());
            statement.setString(6, libro.getCategoria());
            statement.setString(7, libro.getEstado());
            statement.setInt(8, libro.getIdLibro());
            statement.executeUpdate();
        }
    }

    public void eliminarLibro(int idLibro) throws SQLException {
        String query = "DELETE FROM libros WHERE id_libro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idLibro);
            statement.executeUpdate();
        }
    }
}
