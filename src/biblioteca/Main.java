package biblioteca;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = ConexionBD.getConnection();
            // Crear y mostrar el menú principal
            new MenuPrincipal(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
