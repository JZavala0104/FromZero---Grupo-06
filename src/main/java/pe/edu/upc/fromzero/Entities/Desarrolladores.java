package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Desarrolladores")
public class Desarrolladores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdDesarrollador;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "Habilidades", nullable = false,columnDefinition = "TEXT")
    private String Habilidades;
    @Column(name = "Experiencia",nullable = false)
    private int Experiencia;
    @Column(name = "Portafolio",length = 255,nullable = false)
    private String Portafolio;

    public Desarrolladores() {
    }

    public Desarrolladores(int idDesarrollador, Usuarios idUser, String habilidades, int experiencia, String portafolio) {
        IdDesarrollador = idDesarrollador;
        IdUser = idUser;
        Habilidades = habilidades;
        Experiencia = experiencia;
        Portafolio = portafolio;
    }

    public int getIdDesarrollador() {
        return IdDesarrollador;
    }

    public void setIdDesarrollador(int idDesarrollador) {
        IdDesarrollador = idDesarrollador;
    }

    public Usuarios getIdUser() {
        return IdUser;
    }

    public void setIdUser(Usuarios idUser) {
        IdUser = idUser;
    }

    public String getHabilidades() {
        return Habilidades;
    }

    public void setHabilidades(String habilidades) {
        Habilidades = habilidades;
    }

    public int getExperiencia() {
        return Experiencia;
    }

    public void setExperiencia(int experiencia) {
        Experiencia = experiencia;
    }

    public String getPortafolio() {
        return Portafolio;
    }

    public void setPortafolio(String portafolio) {
        Portafolio = portafolio;
    }
}
