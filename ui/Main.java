import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "usuario", "contraseña");
            SwingUtilities.invokeLater(() -> {
                InterfazBibliotecario interfazBibliotecario = new InterfazBibliotecario(connection);
                InterfazLector interfazLector = new InterfazLector(connection);
                interfazBibliotecario.setVisible(true);
                interfazLector.setVisible(true);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
