package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Proyectos")
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdProject;
    @ManyToOne
    @JoinColumn(name = "IdEmpresa")
    private Empresas IdEmpresa;
    @Column(name = "Titulo",length = 150,nullable = false)
    private String Titulo;
    @Column(name = "Descripcion",nullable = false)
    private String Descripcion;
    @Column(name = "Presupuesto",nullable = false)
    private double Presupuesto;
    @Column(name = "Estado",length = 50,nullable = false)
    private String Estado;
    @Column(name = "FechaInicio",nullable = false)
    private LocalDateTime FechaInicio;
    @Column(name = "FechaFin",nullable = false)
    private LocalDateTime FechaFin;

    public Proyectos() {
    }

    public Proyectos(int idProject, Empresas idCompany, String name, String description, double budget, String status, LocalDateTime startDate, LocalDateTime endDate) {
        IdProject = idProject;
        IdEmpresa = idCompany;
        Titulo = name;
        Descripcion = description;
        Presupuesto = budget;
        Estado = status;
        FechaInicio = startDate;
        FechaFin = endDate;
    }

    public int getIdProject() {
        return IdProject;
    }

    public void setIdProject(int idProject) {
        IdProject = idProject;
    }

    public Empresas getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(Empresas idCompany) {
        IdEmpresa = idCompany;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String name) {
        Titulo = name;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String description) {
        Descripcion = description;
    }

    public double getPresupuesto() {
        return Presupuesto;
    }

    public void setPresupuesto(double budget) {
        Presupuesto = budget;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String status) {
        Estado = status;
    }

    public LocalDateTime getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDateTime startDate) {
        FechaInicio = startDate;
    }

    public LocalDateTime getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDateTime endDate) {
        FechaFin = endDate;
    }
}
