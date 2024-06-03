package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Crear la conexión a la base de datos
        Connection connection = null;
        try {
            connection = ConexionBD.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier error al conectar
            return;
        }

        // Crear la interfaz del bibliotecario
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazBibliotecario(connection);
            }
        });

        // Crear la interfaz del lector
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazLector(connection);
            }
        });
    }
}
