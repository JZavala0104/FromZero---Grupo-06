package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.CodigoGenerado;
import java.util.List;
import java.util.Optional;

public interface ICodigoGeneradoService {
    public List<CodigoGenerado> GetCodigoGenerado();
    public CodigoGenerado InsertCodigoGenerado(CodigoGenerado codigoGenerado);
    public void UpdateCodigoGenerado(CodigoGenerado codigoGenerado);
    public void DeleteCodigoGenerado(int idCodigoGenerado);
    public Optional<CodigoGenerado> GetCodigoGeneradoById(int idCodigoGenerado);
}
