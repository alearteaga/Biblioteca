package biblioteca;

<<<<<<< HEAD:src1/biblioteca/PrestamoTableModel.java

=======
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/PrestamoTableModel.java
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PrestamoTableModel extends AbstractTableModel {
    private List<Prestamo> prestamos;
<<<<<<< HEAD:src1/biblioteca/PrestamoTableModel.java
    private final String[] columnNames = {"ID", "ID Libro", "ID Usuario", "Fecha Préstamo", "Fecha Retorno Prevista", "Fecha Retorno Real", "Estado", "Multa"};
=======
    private String[] columnNames = {"ID", "Libro ID", "Usuario ID", "Fecha de Préstamo", "Fecha Prevista de Retorno", "Fecha Real de Retorno", "Estado"};
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/PrestamoTableModel.java

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
<<<<<<< HEAD:src1/biblioteca/PrestamoTableModel.java
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
            case 7:
                return prestamo.getMulta();
            default:
                return null;
        }
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
        fireTableDataChanged();
    }

    public Prestamo getPrestamoAt(int rowIndex) {
        return prestamos.get(rowIndex);
    }
=======
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
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/PrestamoTableModel.java
}
