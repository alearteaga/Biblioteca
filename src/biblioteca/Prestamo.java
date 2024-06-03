package biblioteca;

import java.util.Date;

public class Prestamo {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaRetornoPrevista;
    private Date fechaRetornoReal;
    private String estado;

    public Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaRetornoPrevista, Date fechaRetornoReal, String estado) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
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
