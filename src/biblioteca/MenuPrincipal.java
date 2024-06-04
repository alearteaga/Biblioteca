package biblioteca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class MenuPrincipal extends JFrame {
    private Connection connection;

    public MenuPrincipal(Connection connection) {
        this.connection = connection;

        setTitle("Menú Principal - Gestión de Biblioteca");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el menú
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Gestión");
        menuBar.add(menu);

        JMenuItem menuItemLibros = new JMenuItem("Libros");
        menu.add(menuItemLibros);
        menuItemLibros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InterfazLibros(connection);
            }
        });

        JMenuItem menuItemUsuarios = new JMenuItem("Usuarios");
        menu.add(menuItemUsuarios);
        menuItemUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InterfazUsuarios(connection);
            }
        });

        JMenuItem menuItemPrestamos = new JMenuItem("Préstamos");
        menu.add(menuItemPrestamos);
        menuItemPrestamos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InterfazPrestamos(connection);
            }
        });

        // Mostrar el menú principal
        setVisible(true);
    }
}
