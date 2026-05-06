package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.Proyectos;
import java.util.List;
import java.util.Optional;

public interface IProyectosService {
    public List<Proyectos> GetProyecto();
    public Proyectos InsertProyecto(Proyectos proyecto);
    public void UpdateProyecto(Proyectos proyecto);
    public void DeleteProyecto(int IdProyecto);
    public Optional<Proyectos> GetProyectoById(int IdProyecto);
    List<Object[]> GetQuery3();
}
