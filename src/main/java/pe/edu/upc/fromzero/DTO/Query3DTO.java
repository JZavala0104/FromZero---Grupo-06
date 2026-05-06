package pe.edu.upc.fromzero.DTO;

public class Query3DTO {
    private String Titulo;
    private String Estado;
    private double Presupuesto;
    private String Empresa;

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public double getPresupuesto() {
        return Presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        Presupuesto = presupuesto;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }
}