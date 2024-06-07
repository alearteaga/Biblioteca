<<<<<<< HEAD:src1/biblioteca/Prestamo.java
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
=======
package biblioteca;

import java.util.Date;

public class Prestamo {
    private int id;
    private int libroId;
    private int usuarioId;
    private Date fechaPrestamo;
    private Date fechaPrevistaRetorno;
    private Date fechaRealRetorno;
    private String estado;

    public Prestamo(int id, int libroId, int usuarioId, Date fechaPrestamo, Date fechaPrevistaRetorno, Date fechaRealRetorno, String estado) {
        this.id = id;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevistaRetorno = fechaPrevistaRetorno;
        this.fechaRealRetorno = fechaRealRetorno;
        this.estado = estado;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getLibroId() { return libroId; }
    public void setLibroId(int libroId) { this.libroId = libroId; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public Date getFechaPrevistaRetorno() { return fechaPrevistaRetorno; }
    public void setFechaPrevistaRetorno(Date fechaPrevistaRetorno) { this.fechaPrevistaRetorno = fechaPrevistaRetorno; }
    public Date getFechaRealRetorno() { return fechaRealRetorno; }
    public void setFechaRealRetorno(Date fechaRealRetorno) { this.fechaRealRetorno = fechaRealRetorno; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
>>>>>>> c3098a8f9d69fd1707b5cd8d14d24d8ce515bcf6:src/biblioteca/Prestamo.java
