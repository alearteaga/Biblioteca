package biblioteca;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.lang.model.SourceVersion;

public class LibroTableModel extends AbstractTableModel {
    private List<Libro> libros;
    private final String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Editorial", "Año Publicación", "Categoría", "Estado"};

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
            case 0:
                return libro.getIdLibro();
            case 1:
                return libro.getTitulo();
            case 2:
                return libro.getAutor();
            case 3:
                return libro.getIsbn();
            case 4:
                return libro.getEditorial();
            case 5:
                return libro.getAnioPublicacion();
            case 6:
                return libro.getCategoria();
            case 7:
                return libro.getEstado();
            default:
                return null;
        }
    }

}