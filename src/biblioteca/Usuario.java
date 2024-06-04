package biblioteca;

import java.util.Date;

public class Usuario {
    private int id;
    private int numero;
    private String apellidos;
    private String correo;
    private String telefono;
    private String rol;
    private Date fechaRegistro;

    public Usuario(int id, int numero, String apellidos, String correo, String telefono, String rol, Date fechaRegistro) {
        this.id = id;
        this.numero = numero;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
