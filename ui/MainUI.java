import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    private JFrame frame;
    private JButton btnBibliotecario;
    private JButton btnLector;

    public MainUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        btnBibliotecario = new JButton("Bibliotecario");
        btnBibliotecario.setBounds(48, 50, 150, 25);
        frame.getContentPane().add(btnBibliotecario);

        btnLector = new JButton("Lector");
        btnLector.setBounds(48, 100, 150, 25);
        frame.getContentPane().add(btnLector);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public void setBibliotecarioListener(ActionListener listener) {
        btnBibliotecario.addActionListener(listener);
    }

    public void setLectorListener(ActionListener listener) {
        btnLector.addActionListener(listener);
    }
}
