package biblioteca;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LibroTableModel extends AbstractTableModel {
    private List<Libro> libros;
    private String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Editorial", "Año de Publicación", "Categoría", "Estado"};

    public LibroTableModel(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public int getRowCount() {
        return libros.size();
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
        Libro libro = libros.get(rowIndex);
        switch (columnIndex) {
            case 0: return libro.getId();
            case 1: return libro.getTitulo();
            case 2: return libro.getAutor();
            case 3: return libro.getIsbn();
            case 4: return libro.getEditorial();
            case 5: return libro.getAnioPublicacion();
            case 6: return libro.getCategoria();
            case 7: return libro.getEstado();
            default: return null;
        }
    }
}
