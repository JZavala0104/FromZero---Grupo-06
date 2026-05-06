package pe.edu.upc.fromzero.DTO;

public class Query1DTO {
    private String Empresa;
    private int Total_Proyectos;
    private double Inversion_Total;
    private int Total_Tareas_Asignadas;
    private double Presupuesto_Promedio;

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public int getTotal_Proyectos() {
        return Total_Proyectos;
    }

    public void setTotal_Proyectos(int total_Proyectos) {
        Total_Proyectos = total_Proyectos;
    }

    public double getInversion_Total() {
        return Inversion_Total;
    }

    public void setInversion_Total(double inversion_Total) {
        Inversion_Total = inversion_Total;
    }

    public int getTotal_Tareas_Asignadas() {
        return Total_Tareas_Asignadas;
    }

    public void setTotal_Tareas_Asignadas(int total_Tareas_Asignadas) {
        Total_Tareas_Asignadas = total_Tareas_Asignadas;
    }

    public double getPresupuesto_Promedio() {
        return Presupuesto_Promedio;
    }

    public void setPresupuesto_Promedio(double presupuesto_Promedio) {
        Presupuesto_Promedio = presupuesto_Promedio;
    }
}
