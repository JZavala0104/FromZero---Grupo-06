package pe.edu.upc.fromzero.Entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Revisiones")
public class Revisiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdRevision;
    @ManyToOne
    @JoinColumn(name = "IdTarea")
    private Tareas IdTarea;
    @Column(name = "Comentar",nullable = false,columnDefinition = "TEXT")
    private String Comentar;
    @Column(name = "Estado",length = 50,nullable = false)
    private String Estado;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime Fecha;
    public Revisiones() {
    }
    public Revisiones(int idRevision, Tareas idTarea, String comment, String status, LocalDateTime date) {
        IdRevision = idRevision;
        IdTarea = idTarea;
        Comentar = comment;
        Estado = status;
        Fecha = date;
    }

    public int getIdRevision() {
        return IdRevision;
    }

    public void setIdRevision(int idRevision) {
        IdRevision = idRevision;
    }

    public Tareas getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(Tareas idTarea) {
        IdTarea = idTarea;
    }

    public String getComentar() {
        return Comentar;
    }

    public void setComentar(String comentar) {
        Comentar = comentar;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        Fecha = fecha;
    }
}
