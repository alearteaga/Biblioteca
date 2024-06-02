import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecarioUI {
    private JFrame frame;
    private JButton btnAgregarLibro;
    private JButton btnModificarLibro;
    private JButton btnEliminarLibro;
    private JButton btnVisualizarPrestamos;

    public BibliotecarioUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnAgregarLibro = new JButton("Agregar Libro");
        btnAgregarLibro.setBounds(48, 50, 150, 25);
        frame.getContentPane().add(btnAgregarLibro);

        btnModificarLibro = new JButton("Modificar Libro");
        btnModificarLibro.setBounds(48, 100, 150, 25);
        frame.getContentPane().add(btnModificarLibro);

        btnEliminarLibro = new JButton("Eliminar Libro");
        btnEliminarLibro.setBounds(48, 150, 150, 25);
        frame.getContentPane().add(btnEliminarLibro);

        btnVisualizarPrestamos = new JButton("Visualizar Préstamos");
        btnVisualizarPrestamos.setBounds(48, 200, 150, 25);
        frame.getContentPane().add(btnVisualizarPrestamos);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void setAgregarLibroListener(ActionListener listener) {
        btnAgregarLibro.addActionListener(listener);
    }

    public void setModificarLibroListener(ActionListener listener) {
        btnModificarLibro.addActionListener(listener);
    }

    public void setEliminarLibroListener(ActionListener listener) {
        btnEliminarLibro.addActionListener(listener);
    }

    public void setVisualizarPrestamosListener(ActionListener listener) {
        btnVisualizarPrestamos.addActionListener(listener);
    }
}
