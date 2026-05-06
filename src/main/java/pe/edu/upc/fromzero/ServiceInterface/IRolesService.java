package pe.edu.upc.fromzero.ServiceInterface;

import pe.edu.upc.fromzero.Entities.Roles;
import java.util.List;
import java.util.Optional;

public interface IRolesService {
    public List<Roles> GetRol();
    public Roles InsertRol(Roles rol);
    public void UpdateRol(Roles rol);
    public void DeleteRol(int IdRol);
    public Optional<Roles> GetRolById(int IdRol);
}
