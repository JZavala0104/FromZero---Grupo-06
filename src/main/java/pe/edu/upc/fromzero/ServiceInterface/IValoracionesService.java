package pe.edu.upc.fromzero.ServiceInterface;
import pe.edu.upc.fromzero.Entities.Valoraciones;
import java.util.List;
import java.util.Optional;

public interface IValoracionesService {
    public List<Valoraciones> GetValoracion();
    public Valoraciones InsertValoracion(Valoraciones valoracion);
    public void UpdateValoracion(Valoraciones valoracion);
    public void DeleteValoracion(int IdValoracion);
    public Optional<Valoraciones> GetValoracionById(int IdValoracion);
}
