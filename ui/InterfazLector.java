package biblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class LectorUI extends JFrame {
    private JButton btnBuscarLibro;
    private JButton btnConsultarHistorial;
    private JTextArea textAreaHistorial;

    public LectorUI() {
        setTitle("Interfaz de Lector");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        btnBuscarLibro = new JButton("Buscar Libro");
        btnConsultarHistorial = new JButton("Consultar Historial");
        panelBotones.add(btnBuscarLibro);
        panelBotones.add(btnConsultarHistorial);

        textAreaHistorial = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaHistorial);

        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnBuscarLibro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(LectorUI.this, "Ingrese el título del libro:");
                try {
                    buscarLibro(titulo);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LectorUI.this, "Error al buscar libro.");
                }
            }
        });

        btnConsultarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idUsuario = Integer.parseInt(JOptionPane.showInputDialog(LectorUI.this, "Ingrese su ID de usuario:"));
                try {
                    consultarHistorial(idUsuario);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LectorUI.this, "Error al consultar historial de préstamos.");
                }
            }
        });
    }

    private void buscarLibro(String titulo) throws SQLException {
        // Obtener libros de la base de datos según el título proporcionado
        StringBuilder sb = new StringBuilder();
        List<Libro> libros = LibroDAO.getLibrosByTitulo(titulo);
        if (libros.isEmpty()) {
            sb.append("No se encontraron libros con el título: ").append(titulo);
        } else {
            for (Libro libro : libros) {
                sb.append(libro.toString()).append("\n");
            }
        }
        textAreaHistorial.setText(sb.toString());
    }

    private void consultarHistorial(int idUsuario) throws SQLException {
        // Obtener el historial de préstamos del usuario de la base de datos
        StringBuilder sb = new StringBuilder();
        List<Prestamo> prestamos = PrestamoDAO.getPrestamosByUsuario(idUsuario);
        if (prestamos.isEmpty()) {
            sb.append("No se encontraron préstamos para el usuario con ID: ").append(idUsuario);
        } else {
            for (Prestamo prestamo : prestamos) {
                sb.append(prestamo.toString()).append("\n");
            }
        }
        textAreaHistorial.setText(sb.toString());
    }
}
