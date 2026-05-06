package pe.edu.upc.fromzero.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CodigoGenerado")
public class CodigoGenerado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdCode;
    @ManyToOne
    @JoinColumn(name = "IdUser")
    private Usuarios IdUser;
    @Column(name = "Prompt",nullable = false,columnDefinition = "TEXT")
    private String Prompt;
    @Column(name = "Codigo",nullable = false,columnDefinition = "TEXT")
    private String Codigo;
    @Column(name = "Lenguaje",length = 50,nullable = false)
    private String Lenguaje;
    @Column(name = "Fecha",nullable = false)
    private LocalDateTime Fecha;

    public CodigoGenerado() {
    }

    public CodigoGenerado(LocalDateTime fecha, String lenguaje, String codigo, String prompt, Usuarios idUser, int idCode) {
        Fecha = fecha;
        Lenguaje = lenguaje;
        Codigo = codigo;
        Prompt = prompt;
        IdUser = idUser;
        IdCode = idCode;
    }

    public int getIdCode() {
        return IdCode;
    }

    public void setIdCode(int idCode) {
        IdCode = idCode;
    }

    public Usuarios getIdUser() {
        return IdUser;
    }

    public void setIdUser(Usuarios idUser) {
        IdUser = idUser;
    }

    public String getPrompt() {
        return Prompt;
    }

    public void setPrompt(String prompt) {
        Prompt = prompt;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String code) {
        Codigo = code;
    }

    public String getLenguaje() {
        return Lenguaje;
    }

    public void setLenguaje(String language) {
        Lenguaje = language;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime date) {
        Fecha = date;
    }
}
