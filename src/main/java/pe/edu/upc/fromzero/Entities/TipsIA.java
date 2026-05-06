package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TipsIA")
public class TipsIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdTip;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "Contenido",nullable = false,columnDefinition = "TEXT")
    private String Contenido;
    @Column(name = "Fecha", nullable = false)
    private LocalDate Fecha;
    public TipsIA() {
    }

    public TipsIA(int idTip, Usuarios idUser, String content, LocalDate date) {
        IdTip = idTip;
        IdUser = idUser;
        Contenido = content;
        Fecha = date;
    }

    public int getIdTip() {
        return IdTip;
    }

    public void setIdTip(int idTip) {
        IdTip = idTip;
    }

    public Usuarios getIdUser() {
        return IdUser;
    }

    public void setIdUser(Usuarios idUser) {
        IdUser = idUser;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String content) {
        Contenido = content;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate date) {
        Fecha = date;
    }
}
