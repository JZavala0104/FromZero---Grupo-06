package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Valoraciones")
public class Valoraciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdValoracion;
    @ManyToOne
    @JoinColumn(name = "IdProyecto")
    private Proyectos IdProyecto;
    @Column(name = "Puntuacion",nullable = false)
    private double Puntuacion;
    @Column(name = "Comentario",nullable = false,columnDefinition = "TEXT")
    private String Comentario;

    public Valoraciones() {
    }

    public Valoraciones(int idValoracion, Proyectos idProyecto, double puntuacion, String comentario) {
        IdValoracion = idValoracion;
        IdProyecto = idProyecto;
        Puntuacion = puntuacion;
        Comentario = comentario;
    }

    public int getIdValoracion() {
        return IdValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        IdValoracion = idValoracion;
    }

    public Proyectos getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(Proyectos idProyecto) {
        IdProyecto = idProyecto;
    }

    public double getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        Puntuacion = puntuacion;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }
}
