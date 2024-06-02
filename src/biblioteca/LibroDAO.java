package biblioteca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {
    public static void insertLibro(Libro libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "INSERT INTO Libros (Titulo, Autor, ISBN, Editorial, Año_Publicacion, Categoria, Estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAñoPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
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

    public static void updateLibro(Libro libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "UPDATE Libros SET Titulo=?, Autor=?, ISBN=?, Editorial=?, Año_Publicacion=?, Categoria=?, Estado=? WHERE ID_Libro=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getIsbn());
            stmt.setString(4, libro.getEditorial());
            stmt.setInt(5, libro.getAñoPublicacion());
            stmt.setString(6, libro.getCategoria());
            stmt.setString(7, libro.getEstado());
            stmt.setInt(8, libro.getId());
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

    public static void deleteLibro(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "DELETE FROM Libros WHERE ID_Libro=?";
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

    public static List<Libro> getLibros() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Libro> libros = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM Libros";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_Libro");
                String titulo = rs.getString("Titulo");
                String autor = rs.getString("Autor");
                String isbn = rs.getString("ISBN");
                String editorial = rs.getString("Editorial");
                int añoPublicacion = rs.getInt("Año_Publicacion");
                String categoria = rs.getString("Categoria");
                String estado = rs.getString("Estado");

                Libro libro = new Libro(id, titulo, autor, isbn, editorial, añoPublicacion, categoria, estado);
                libros.add(libro);
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

        return libros;
    }
}