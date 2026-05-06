package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notificaciones")
public class Notificaciones {
    @Id
    @GeneratedValue
    private int IdNotification;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "Mensaje",nullable = false,columnDefinition = "TEXT")
    private String Mensaje;
    @Column(name = "Leido",nullable = false)
    private boolean Leido;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime Fecha;

    public Notificaciones() {
    }

    public Notificaciones(int idNotification, Usuarios idUser, String content, boolean read, LocalDateTime date) {
        IdNotification = idNotification;
        IdUser = idUser;
        Mensaje = content;
        Leido = read;
        Fecha = date;
    }

    public int getIdNotification() {
        return IdNotification;
    }

    public void setIdNotification(int idNotification) {
        IdNotification = idNotification;
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

    public void setMensaje(String content) {
        Mensaje = content;
    }

    public boolean isLeido() {
        return Leido;
    }

    public void setLeido(boolean read) {
        Leido = read;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime date) {
        Fecha = date;
    }
}
