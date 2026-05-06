package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdRol;
    @Column(name = "Nombre",length = 50,nullable = false)
    private String Nombre;

    public Roles() {
    }

    public Roles(int ID, String name) {
        this.IdRol = ID;
        Nombre = name;
    }

    public int getID() {
        return IdRol;
    }

    public void setID(int ID) {
        this.IdRol = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String name) {
        Nombre = name;
    }
}
