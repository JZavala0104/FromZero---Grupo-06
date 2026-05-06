package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Tareas")
public class Tareas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdTarea;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos IdProyecto;
    @Column(name = "Titulo",length = 150,nullable = false)
    private String Titulo;
    @Column(name = "Descripcion",nullable = false)
    private String Descripcion;
    @Column(name = "FechaLimite",nullable = false)
    private String FechaLimite;
    @Column(name = "Estado",length = 50,nullable = false)
    private String Estado;
    public Tareas() {
    }
    public Tareas(int idTarea, Proyectos idProyecto, String title, String description, String deadline, String status) {
        IdTarea = idTarea;
        IdProyecto = idProyecto;
        Titulo = title;
        Descripcion = description;
        FechaLimite = deadline;
        Estado = status;
    }

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int idTarea) {
        IdTarea = idTarea;
    }

    public Proyectos getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        IdProyecto = idProyecto;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFechaLimite() {
        return FechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        FechaLimite = fechaLimite;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
