package pe.edu.upc.fromzero.DTO;

import java.time.LocalDateTime;

public class RevisionesDTO {
    private int idRevision;
    private int idTarea;
    private String comentar;
    private String estado;
    private LocalDateTime fecha;

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
