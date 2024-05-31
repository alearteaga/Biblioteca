import java.util.Date;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String editorial;
    private Date fechaPublicacion;

    public Libro(int id, String titulo, String autor, String editorial, Date fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
}
