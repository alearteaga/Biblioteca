package biblioteca;

public class Prestamo {
    private int id;
    private int idLibro;
    private int idUsuario;
    private String fechaPrestamo;
    private String fechaRetornoPrevista;
    private String fechaRetornoReal;
    private String estado;

    public Prestamo(int id, int idLibro, int idUsuario, String fechaPrestamo, String fechaRetornoPrevista, String fechaRetornoReal, String estado) {
        this.id = id;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaRetornoPrevista() {
        return fechaRetornoPrevista;
    }

    public void setFechaRetornoPrevista(String fechaRetornoPrevista) {
        this.fechaRetornoPrevista = fechaRetornoPrevista;
    }

    public String getFechaRetornoReal() {
        return fechaRetornoReal;
    }

    public void setFechaRetornoReal(String fechaRetornoReal) {
        this.fechaRetornoReal = fechaRetornoReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
