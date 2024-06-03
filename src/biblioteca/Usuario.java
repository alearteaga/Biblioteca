package biblioteca;

import java.util.Date;

public class Usuario {
    private int idUsuario;
    private int numero;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private String rol;
    private Date fechaRegistro;

    public Usuario(int idUsuario, int numero, String apellidos, String correoElectronico, String telefono, String rol, Date fechaRegistro) {
        this.idUsuario = idUsuario;
        this.numero = numero;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.rol = rol;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNumero() {
        return numero;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
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
