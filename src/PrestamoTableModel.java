import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PrestamoTableModel extends AbstractTableModel {
    private final List<Prestamo> prestamos;
    private final String[] columnNames = {"ID", "Usuario ID", "Libro ID", "Fecha Préstamo", "Fecha Retorno Prevista", "Fecha Retorno Real", "Estado"};

    public PrestamoTableModel(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestamo prestamo = prestamos.get(rowIndex);
        switch (columnIndex) {
            case 0: return prestamo.getId();
            case 1: return prestamo.getUsuarioId();
            case 2: return prestamo.getLibroId();
            case 3: return prestamo.getFechaPrestamo();
            case 4: return prestamo.getFechaRetornoPrevista();
            case 5: return prestamo.getFechaRetornoReal();
            case 6: return prestamo.getEstado();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}