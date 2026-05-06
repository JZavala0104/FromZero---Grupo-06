package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdUser;
    @Column(name = "Nombre",length = 100,nullable = false)
    private String Nombre;
    @Column(name = "Email",length = 100,nullable = false)
    private String Email;
    @Column(name = "Password",length = 255,nullable = false)
    private String Password;
    @Column(name = "FechaRegistro",nullable = false)
    private LocalDateTime FechaRegistro;
    @ManyToOne
    @JoinColumn(name = "IdRol")
    private Roles IdRol;

    public Usuarios() {
    }

    public Usuarios(int idUser, String name, String email, String password, LocalDateTime registerDate, Roles idRol) {
        IdUser = idUser;
        Nombre = name;
        Email = email;
        Password = password;
        FechaRegistro = registerDate;
        IdRol = idRol;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int idUser) {
        IdUser = idUser;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String name) {
        Nombre = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public LocalDateTime getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime registerDate) {
        FechaRegistro = registerDate;
    }

    public Roles getIdRol() {
        return IdRol;
    }

    public void setIdRol(Roles idRol) {
        IdRol = idRol;
    }
}
