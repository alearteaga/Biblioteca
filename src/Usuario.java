import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String rol;
    private Date fechaRegistro;

    public Usuario(int id, String nombre, String apellidos, String email, String telefono, String rol, Date fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }
}
