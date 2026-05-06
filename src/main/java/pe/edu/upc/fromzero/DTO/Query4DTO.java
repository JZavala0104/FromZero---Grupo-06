package pe.edu.upc.fromzero.DTO;

public class Query4DTO {
    private String Proyecto;
    private String Tarea;
    private String Estado;
    private String FechaLimite;

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String proyecto) {
        Proyecto = proyecto;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFechaLimite() {
        return FechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        FechaLimite = fechaLimite;
    }
}