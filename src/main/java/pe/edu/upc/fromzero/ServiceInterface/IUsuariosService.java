package pe.edu.upc.fromzero.ServiceInterface;
import pe.edu.upc.fromzero.Entities.Usuarios;
import java.util.List;
import java.util.Optional;

public interface IUsuariosService {
    public List<Usuarios> GetUsuario();
    public Usuarios InsertUsuario(Usuarios usuario);
    public void UpdateUsuario(Usuarios usuario);
    public void DeleteUsuario(int IdUsuario);
    public Optional<Usuarios> GetUsuarioById(int IdUsuario);
}
