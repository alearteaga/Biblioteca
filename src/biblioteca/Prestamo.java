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
