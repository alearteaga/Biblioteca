package biblioteca;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConexionBD.getConnection();
            InterfazBibliotecario interfazBibliotecario = new InterfazBibliotecario(connection);
            interfazBibliotecario.mostrarMenu();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
