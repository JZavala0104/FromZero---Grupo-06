package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Empresas")
public class Empresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdEmpresa;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "NombreEmpresa",length = 150,nullable = false)
    private String NombreEmpresa;
    @Column(name = "Descripcion",nullable = false,columnDefinition = "TEXT")
    private String Descripcion;

    public Empresas() {
    }

    public Empresas(int idCompany, Usuarios idUser, String name, String description) {
        IdEmpresa = idCompany;
        IdUser = idUser;
        NombreEmpresa = name;
        Descripcion = description;
    }

    public int getIdEmpresa() {
        return IdEmpresa;
    }

    public void setIdEmpresa(int idCompany) {
        IdEmpresa = idCompany;
    }

    public Usuarios getIdUser() {
        return IdUser;
    }

    public void setIdUser(Usuarios idUser) {
        IdUser = idUser;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String name) {
        NombreEmpresa = name;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String description) {
        Descripcion = description;
    }
}
