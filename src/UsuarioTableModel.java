import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UsuarioTableModel extends AbstractTableModel {
    private final List<Usuario> usuarios;
    private final String[] columnNames = {"ID", "Nombre", "Apellidos", "Email", "Teléfono", "Rol", "Fecha de Registro"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0: return usuario.getId();
            case 1: return usuario.getNombre();
            case 2: return usuario.getApellidos();
            case 3: return usuario.getEmail();
            case 4: return usuario.getTelefono();
            case 5: return usuario.getRol();
            case 6: return usuario.getFechaRegistro();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
