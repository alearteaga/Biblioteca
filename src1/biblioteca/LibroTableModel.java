package biblioteca;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LibroTableModel extends AbstractTableModel {
    private List<Libro> libros;
<<<<<<< HEAD:src1/biblioteca/LibroTableModel.java
    private final String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Editorial", "Año Publicación", "Categoría", "Estado"};
=======
    private String[] columnNames = {"ID", "Título", "Autor", "ISBN", "Editorial", "Año de Publicación", "Categoría", "Estado"};
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/LibroTableModel.java

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
<<<<<<< HEAD:src1/biblioteca/LibroTableModel.java
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
                return libro.getAñoPublicacion();
            case 6:
                return libro.getCategoria();
            case 7:
                return libro.getEstado();
            default:
                return null;
        }
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
        fireTableDataChanged();
    }

    public Libro getLibroAt(int rowIndex) {
        return libros.get(rowIndex);
    }
=======
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
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/LibroTableModel.java
}
