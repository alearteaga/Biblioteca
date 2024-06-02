import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LectorUI {
    private JFrame frame;
    private JButton btnBuscarLibros;
    private JButton btnConsultarHistorial;

    public LectorUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnBuscarLibros = new JButton("Buscar Libros");
        btnBuscarLibros.setBounds(48, 50, 150, 25);
        frame.getContentPane().add(btnBuscarLibros);

        btnConsultarHistorial = new JButton("Consultar Historial");
        btnConsultarHistorial.setBounds(48, 100, 150, 25);
        frame.getContentPane().add(btnConsultarHistorial);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void setBuscarLibrosListener(ActionListener listener) {
        btnBuscarLibros.addActionListener(listener);
    }

    public void setConsultarHistorialListener(ActionListener listener) {
        btnConsultarHistorial.addActionListener(listener);
    }
}
