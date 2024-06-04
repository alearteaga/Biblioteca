package biblioteca;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private String editorial;
    private int anioPublicacion;
    private String categoria;
    private String estado;

    public Libro(int id, String titulo, String autor, String isbn, String editorial, int anioPublicacion, String categoria, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Libro(int int1, String text, String text2, String text3, String text4, int int2, int int3) {
        //TODO Auto-generated constructor stub
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
