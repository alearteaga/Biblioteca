package biblioteca;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {
    private List<Usuario> usuarios;
    private final String[] columnNames = {"ID", "Número", "Apellidos", "Correo Electrónico", "Teléfono", "Rol", "Fecha de Registro"};

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
                return usuario.getNumero();
            case 2:
                return usuario.getApellidos();
            case 3:
                return usuario.getCorreoElectronico();
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