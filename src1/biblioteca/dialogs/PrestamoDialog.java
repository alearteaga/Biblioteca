package biblioteca.dialogs;

import biblioteca.Prestamo;
import biblioteca.PrestamoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class PrestamoDialog extends JDialog {
    private JTextField txtIdLibro;
    private JTextField txtIdUsuario;
    private JTextField txtFechaPrestamo;
    private JTextField txtFechaRetornoPrevista;
    private JTextField txtFechaRetornoReal;
    private JTextField txtEstado;
    private JTextField txtMulta;
    private JButton btnGuardar;
    private PrestamoDAO prestamoDAO;
    private Prestamo prestamo;

    public PrestamoDialog(Frame parent, PrestamoDAO prestamoDAO, Prestamo prestamo) {
        super(parent, true);
        this.prestamoDAO = prestamoDAO;
        this.prestamo = prestamo;
        setTitle(prestamo == null ? "Agregar Prestamo" : "Modificar Prestamo");
        initComponents();
        if (prestamo != null) {
            cargarDatosPrestamo();
        }
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("ID Libro:"));
        txtIdLibro = new JTextField();
        panel.add(txtIdLibro);
        panel.add(new JLabel("ID Usuario:"));
        txtIdUsuario = new JTextField();
        panel.add(txtIdUsuario);
        panel.add(new JLabel("Fecha Prestamo (yyyy-mm-dd):"));
        txtFechaPrestamo = new JTextField();
        panel.add(txtFechaPrestamo);
        panel.add(new JLabel("Fecha Retorno Prevista (yyyy-mm-dd):"));
        txtFechaRetornoPrevista = new JTextField();
        panel.add(txtFechaRetornoPrevista);
        panel.add(new JLabel("Fecha Retorno Real (yyyy-mm-dd):"));
        txtFechaRetornoReal = new JTextField();
        panel.add(txtFechaRetornoReal);
        panel.add(new JLabel("Estado:"));
        txtEstado = new JTextField();
        panel.add(txtEstado);
        panel.add(new JLabel("Multa:"));
        txtMulta = new JTextField();
        panel.add(txtMulta);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPrestamo();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void cargarDatosPrestamo() {
        txtIdLibro.setText(String.valueOf(prestamo.getIdLibro()));
        txtIdUsuario.setText(String.valueOf(prestamo.getIdUsuario()));
        txtFechaPrestamo.setText(prestamo.getFechaPrestamo().toString());
        txtFechaRetornoPrevista.setText(prestamo.getFechaRetornoPrevista().toString());
        txtFechaRetornoReal.setText(prestamo.getFechaRetornoReal().toString());
        txtEstado.setText(prestamo.getEstado());
        txtMulta.setText(String.valueOf(prestamo.getMulta()));
    }

    private void guardarPrestamo() {
        if (prestamo == null) {
            prestamo = new Prestamo();
        }
        prestamo.setIdLibro(Integer.parseInt(txtIdLibro.getText()));
        prestamo.setIdUsuario(Integer.parseInt(txtIdUsuario.getText()));
        prestamo.setFechaPrestamo(Date.valueOf(txtFechaPrestamo.getText()));
        prestamo.setFechaRetornoPrevista(Date.valueOf(txtFechaRetornoPrevista.getText()));
        prestamo.setFechaRetornoReal(Date.valueOf(txtFechaRetornoReal.getText()));
        prestamo.setEstado(txtEstado.getText());
        prestamo.setMulta(Double.parseDouble(txtMulta.getText()));

        try {
            if (prestamo.getIdPrestamo() == 0) {
                prestamoDAO.agregarPrestamo(prestamo);
            } else {
                prestamoDAO.modificarPrestamo(prestamo);
            }
            dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el prestamo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
