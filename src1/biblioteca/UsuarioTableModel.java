package biblioteca;

<<<<<<< HEAD:src1/biblioteca/UsuarioTableModel.java
import java.sql.Connection;
=======
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/UsuarioTableModel.java
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {
    private List<Usuario> usuarios;
<<<<<<< HEAD:src1/biblioteca/UsuarioTableModel.java
    private String[] columnNames = {"ID", "Nombre", "Apellidos", "Correo", "Teléfono", "Rol", "Fecha Registro"};
    private Connection conexion;


        public UsuarioTableModel(List<Usuario> usuarios) {
            this.usuarios = usuarios;
        }
=======
    private String[] columnNames = {"ID", "Número", "Apellidos", "Correo", "Teléfono", "Rol", "Fecha de Registro"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/UsuarioTableModel.java

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
<<<<<<< HEAD:src1/biblioteca/UsuarioTableModel.java
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
                return usuario.getCorreo();
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

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
=======
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0: return usuario.getId();
            case 1: return usuario.getNumero();
            case 2: return usuario.getApellidos();
            case 3: return usuario.getCorreo();
            case 4: return usuario.getTelefono();
            case 5: return usuario.getRol();
            case 6: return usuario.getFechaRegistro();
            default: return null;
        }
    }
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/UsuarioTableModel.java
}
