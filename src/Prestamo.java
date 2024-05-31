import java.util.Date;

public class Prestamo {
    private int id;
    private int usuarioId;
    private int libroId;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(int id, int usuarioId, int libroId, Date fechaPrestamo, Date fechaDevolucion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
}
