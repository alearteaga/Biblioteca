import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class InterfazLector extends JFrame {
    private JButton btnBuscarLibros, btnConsultarHistorial;
    private Connection connection;

    public InterfazLector(Connection connection) {
        this.connection = connection;
        setTitle("Interfaz de Lector");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnBuscarLibros = new JButton("Buscar Libros Disponibles");
        btnConsultarHistorial = new JButton("Consultar Historial de Préstamos");

        panel.add(btnBuscarLibros);
        panel.add(btnConsultarHistorial);

        btnBuscarLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para buscar libros disponibles
                new VentanaBuscarLibros(connection);
            }
        });

        btnConsultarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abre una nueva ventana para consultar historial de préstamos
                new VentanaHistorialPrestamos(connection);
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Se requiere una conexión a la base de datos aquí
        Connection connection = null;
        try {
            // Establecer conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "usuario", "contraseña");
            new InterfazLector(connection);
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
