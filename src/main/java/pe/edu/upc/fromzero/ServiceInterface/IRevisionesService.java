package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.Revisiones;
import java.util.List;
import java.util.Optional;

public interface IRevisionesService {
    public List<Revisiones> GetRevision();
    public Revisiones InsertRevision(Revisiones revision);
    public void UpdateRevision(Revisiones revision);
    public void DeleteRevision(int IdRevision);
    public Optional<Revisiones> GetRevisionById(int IdRevision);
    List<Object[]> GetQuery6();
}
