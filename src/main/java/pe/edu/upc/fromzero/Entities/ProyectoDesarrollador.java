package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ProyectoDesarrollador")
public class ProyectoDesarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdProyDesar;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos IdProyecto;
    @ManyToOne
    @JoinColumn(name = "IdDesarrollador")
    private Desarrolladores IdDesarrollador;

    public ProyectoDesarrollador() {
    }

    public ProyectoDesarrollador(int idProyDesar, Proyectos idProyecto, Desarrolladores idDesarrollador) {
        IdProyDesar = idProyDesar;
        IdProyecto = idProyecto;
        IdDesarrollador = idDesarrollador;
    }

    public int getIdProyDesar() {
        return IdProyDesar;
    }

    public void setIdProyDesar(int idProyDesar) {
        IdProyDesar = idProyDesar;
    }

    public Proyectos getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        IdProyecto = idProyecto;
    }

    public Desarrolladores getIdDesarrollador() {
        return IdDesarrollador;
    }

    public void setIdDesarrollador(Desarrolladores idDesarrollador) {
        IdDesarrollador = idDesarrollador;
    }
}
