import java.util.Date;

public class Prestamo {
    private int id;
    private int usuarioId;
    private int libroId;
    private Date fechaPrestamo;
    private Date fechaRetornoPrevista;
    private Date fechaRetornoReal;
    private String estado;

    public Prestamo(int id, int usuarioId, int libroId, Date fechaPrestamo, Date fechaRetornoPrevista, Date fechaRetornoReal, String estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getLibroId() {
        return libroId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaRetornoPrevista() {
        return fechaRetornoPrevista;
    }

    public Date getFechaRetornoReal() {
        return fechaRetornoReal;
    }

    public String getEstado() {
        return estado;
    }
}
