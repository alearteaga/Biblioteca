package biblioteca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Connection connection;

    public LibroDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO libros (id_libro, titulo, autor, isbn, editorial, anio_publicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, libro.getIdLibro());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setString(4, libro.getIsbn());
            statement.setString(5, libro.getEditorial());
            statement.setInt(6, libro.getAnioPublicacion());
            statement.setString(7, libro.getCategoria());
            statement.setString(8, libro.getEstado());
            statement.executeUpdate();
        }
    }

    public void modificarLibro(Libro libro) throws SQLException {
        String query = "UPDATE libros SET titulo = ?, autor = ?, isbn = ?, editorial = ?, anio_publicacion = ?, categoria = ?, estado = ? WHERE id_libro = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setString(4, libro.getEditorial());
            statement.setInt(5, libro.getAnioPublicacion());
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

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM libros";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    libros.add(new Libro(
                            resultSet.getInt("id_libro"),
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getString("isbn"),
                            resultSet.getString("editorial"),
                            resultSet.getInt("anio_publicacion"),
                            resultSet.getString("categoria"),
                            resultSet.getString("estado")
                    ));
                }
            }
        }
        return libros;
    }
}
