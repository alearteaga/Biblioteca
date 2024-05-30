public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private int anyPublicacion;
    private String categoria;
    private String estado;

    // Constructor
    public Libro(int idLibro, String titulo, String autor, String isbn, String editorial, int anyPublicacion, String categoria, String estado) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyPublicacion = anyPublicacion;
        this.categoria = categoria;
        this.estado = estado;
    }

    // Getters y setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnyPublicacion() {
        return anyPublicacion;
    }

    public void setAnyPublicacion(int anyPublicacion) {
        this.anyPublicacion = anyPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", autor=" + autor + ", isbn=" + isbn
                + ", editorial=" + editorial + ", anyPublicacion=" + anyPublicacion + ", categoria=" + categoria
                + ", estado=" + estado + "]";
    }
}
