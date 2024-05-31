import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuariosTableModel extends AbstractTableModel {
    private List<Usuario> usuarios;
    private String[] columnNames = {"ID", "Nombre", "Apellidos", "Email", "Teléfono", "Rol", "Fecha Registro"};

    public UsuariosTableModel(UsuarioDAO usuarioDAO) {
        try {
            usuarios = usuarioDAO.obtenerTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getIdUsuario();
            case 1:
                return usuario.getNombre();
            case 2:
                return usuario.getApellidos();
            case 3:
                return usuario.getEmail();
            case 4:
                return usuario.getTelefono();
            case 5:
                return usuario.getRol();
             case 6:
                return usuario.getFechaRegistro();
            default:
                return null;
        }
    }
}