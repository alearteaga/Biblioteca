package biblioteca;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConexionBD.getConnection();
            // Crear y mostrar la interfaz de Libros
            new InterfazLibros(connection);
            // Crear y mostrar la interfaz de Usuarios
            new InterfazUsuarios(connection);
            // Crear y mostrar la interfaz de Préstamos
            new InterfazPrestamos(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
