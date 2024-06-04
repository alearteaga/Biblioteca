package biblioteca;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrestamoTableModel extends AbstractTableModel {
    private List<Prestamo> prestamos;
    private String[] columnNames = {"ID", "Libro ID", "Usuario ID", "Fecha de Préstamo", "Fecha Prevista de Retorno", "Fecha Real de Retorno", "Estado"};

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
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestamo prestamo = prestamos.get(rowIndex);
        switch (columnIndex) {
            case 0: return prestamo.getId();
            case 1: return prestamo.getLibroId();
            case 2: return prestamo.getUsuarioId();
            case 3: return prestamo.getFechaPrestamo();
            case 4: return prestamo.getFechaPrevistaRetorno();
            case 5: return prestamo.getFechaRealRetorno();
            case 6: return prestamo.getEstado();
            default: return null;
        }
    }
}
