package pe.edu.upc.fromzero.DTO;

public class Query2DTO {
    private String Desarrollador;
    private int Años_Exp;
    private String Skills;
    private int Proyectos_Participados;
    private double Reputacion_Promedio;
    private int Cantidad_Valoraciones;

    public String getDesarrollador() {
        return Desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        Desarrollador = desarrollador;
    }

    public int getAños_Exp() {
        return Años_Exp;
    }

    public void setAños_Exp(int años_Exp) {
        Años_Exp = años_Exp;
    }

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public int getProyectos_Participados() {
        return Proyectos_Participados;
    }

    public void setProyectos_Participados(int proyectos_Participados) {
        Proyectos_Participados = proyectos_Participados;
    }

    public double getReputacion_Promedio() {
        return Reputacion_Promedio;
    }

    public void setReputacion_Promedio(double reputacion_Promedio) {
        Reputacion_Promedio = reputacion_Promedio;
    }

    public int getCantidad_Valoraciones() {
        return Cantidad_Valoraciones;
    }

    public void setCantidad_Valoraciones(int cantidad_Valoraciones) {
        Cantidad_Valoraciones = cantidad_Valoraciones;
    }
}
