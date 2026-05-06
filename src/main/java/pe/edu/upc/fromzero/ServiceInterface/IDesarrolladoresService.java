package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.Desarrolladores;
import java.util.List;
import java.util.Optional;

public interface IDesarrolladoresService {
    public List<Desarrolladores> GetDesarrollador();
    public Desarrolladores InsertDesarrollador(Desarrolladores desarrollador);
    public void UpdateDesarrollador(Desarrolladores desarrollador);
    public void DeleteDesarrollador(int IdDesarrollador);
    public Optional<Desarrolladores> GetDesarrolladorById(int IdDesarrollador);
    List<Object[]> GetQuery2();
}
