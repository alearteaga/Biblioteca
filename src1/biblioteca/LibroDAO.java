package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    private Connection conexion;

    public LibroDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarLibro(Libro libro) throws SQLException {
        String sql = "INSERT INTO Libros (titulo, autor, isbn, editorial, año_publicacion, categoria, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAñoPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
            stmt.executeUpdate();
        }
    }

    public void eliminarLibro(int id) throws SQLException {
        String sql = "DELETE FROM Libros WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void modificarLibro(Libro libro) throws SQLException {
        String sql = "UPDATE Libros SET titulo = ?, autor = ?, isbn = ?, editorial = ?, año_publicacion = ?, categoria = ?, estado = ? WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAñoPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
            stmt.setInt(8, libro.getIdLibro());
            stmt.executeUpdate();
        }
    }

    public List<Libro> obtenerTodosLosLibros() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setIdLibro(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setAñoPublicacion(rs.getInt("año_publicacion"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setEstado(rs.getString("estado"));
                libros.add(libro);
            }
        }
        return libros;
    }
    
    public Libro obtenerLibroPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Libros WHERE id_libro = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Libro libro = new Libro();
                    libro.setIdLibro(rs.getInt("id_libro"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setIsbn(rs.getString("isbn"));
                    libro.setEditorial(rs.getString("editorial"));
                    libro.setAñoPublicacion(rs.getInt("año_publicacion"));
                    libro.setCategoria(rs.getString("categoria"));
                    libro.setEstado(rs.getString("estado"));
                    return libro;
                } else {
                    return null;
                }
            }
        }
    }
}
