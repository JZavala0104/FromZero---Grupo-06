package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensajes")
public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdMensaje;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos IdProyecto;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "Mensaje",nullable = false,columnDefinition = "TEXT")
    private String Mensaje;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime Fecha;
    public Mensajes() {
    }
    public Mensajes(int idMensaje, Proyectos idProyecto, Usuarios idUser, String content, LocalDateTime date) {
        IdMensaje = idMensaje;
        IdProyecto = idProyecto;
        IdUser = idUser;
        Mensaje = content;
        Fecha = date;
    }

    public int getIdMensaje() {
        return IdMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        IdMensaje = idMensaje;
    }

    public Proyectos getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        IdProyecto = idProyecto;
    }

    public Usuarios getIdUser() {
        return IdUser;
    }

    public void setIdUser(Usuarios idUser) {
        IdUser = idUser;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        Fecha = fecha;
    }
}
