package pe.edu.upc.fromzero.DTO;

public class Query2DTO {
    private String Nombre;
    private String Habilidades;
    private int Experiencia;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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
}
