package biblioteca;

import java.sql.Date;

public class Prestamo {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaRetornoPrevista;
    private Date fechaRetornoReal;
    private String estado;
    private double multa;

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

    public void setFechaPrestamo(java.util.Date date) {
        this.fechaPrestamo = (Date) date;
    }

    public Date getFechaRetornoPrevista() {
        return fechaRetornoPrevista;
    }

    public void setFechaRetornoPrevista(java.util.Date date) {
        this.fechaRetornoPrevista = (Date) date;
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

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }
}
