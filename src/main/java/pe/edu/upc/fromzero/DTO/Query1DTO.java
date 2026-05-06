package pe.edu.upc.fromzero.DTO;

public class Query1DTO {
    private String Proyecto;
    private String Cliente;
    private String Desarrollador;

    public String getDesarrollador() {
        return Desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        Desarrollador = desarrollador;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String proyecto) {
        Proyecto = proyecto;
    }
}
