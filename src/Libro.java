public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private int anoPublicacion;
    private String categoria;
    private String estado;

    public Libro(int id, String titulo, String autor, String isbn, String editorial, int anoPublicacion, String categoria, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anoPublicacion = anoPublicacion;
        this.categoria = categoria;
        this.estado = estado;
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

    public String getIsbn() {
        return isbn;
    }

    public String getEditorial() {
        return editorial;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
