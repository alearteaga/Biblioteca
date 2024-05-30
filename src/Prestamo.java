public class Prestamo {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private String fechaPrestamo;
    private String fechaRetornoPrevista;
    private String fechaRetornoReal;
    private String estado;

    // Constructor
    public Prestamo(int idPrestamo, int idLibro, int idUsuario, String fechaPrestamo, String fechaRetornoPrevista, String fechaRetornoReal, String estado) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    // Getters y setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
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

    @Override
    public String toString() {
        return "Prestamo [idPrestamo=" + idPrestamo + ", idLibro=" + idLibro + ", idUsuario=" + idUsuario
                + ", fechaPrestamo=" + fechaPrestamo + ", fechaRetornoPrevista=" + fechaRetornoPrevista
                + ", fechaRetornoReal=" + fechaRetornoReal + ", estado=" + estado + "]";
    }
}
