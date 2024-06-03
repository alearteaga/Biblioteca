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

    // Constructor
    public Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaRetornoPrevista, Date fechaRetornoReal, String estado) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRetornoPrevista = fechaRetornoPrevista;
        this.fechaRetornoReal = fechaRetornoReal;
        this.estado = estado;
    }

    // Getters y Setters
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

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaRetornoPrevista() {
        return fechaRetornoPrevista;
    }

    public void setFechaRetornoPrevista(Date fechaRetornoPrevista) {
        this.fechaRetornoPrevista = fechaRetornoPrevista;
    }

    public Date getFechaRetornoReal() {
        return fechaRetornoReal;
    }

    public void setFechaRetornoReal(Date fechaRetornoReal) {
        this.fechaRetornoReal = fechaRetornoReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
