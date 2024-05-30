import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class PrestamosTableModel extends AbstractTableModel {
    private List<Prestamo> prestamos;
    private String[] columnNames = {"ID", "ID Libro", "ID Usuario", "Fecha Préstamo", "Fecha Retorno Prevista", "Fecha Retorno Real", "Estado"};

    public PrestamosTableModel(PrestamoDAO prestamoDAO) {
        try {
            prestamos = prestamoDAO.obtenerTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return prestamos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestamo prestamo = prestamos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prestamo.getIdPrestamo();
            case 1:
                return prestamo.getIdLibro();
            case 2:
                return prestamo.getIdUsuario();
            case 3:
                return prestamo.getFechaPrestamo();
            case 4:
                return prestamo.getFechaRetornoPrevista();
            case 5:
                return prestamo.getFechaRetornoReal();
            case 6:
                return prestamo.getEstado();
            default:
                return null;
        }
    }
}
