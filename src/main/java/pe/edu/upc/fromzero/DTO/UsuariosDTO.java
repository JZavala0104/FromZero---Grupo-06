package pe.edu.upc.fromzero.DTO;

import java.time.LocalDateTime;

public class UsuariosDTO {
    private int IdUser;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaRegistro;
    private int idRol;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        nombre = Nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime FechaRegistro) {
        fechaRegistro = FechaRegistro;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int IdRol) {
        idRol = IdRol;
    }
}
