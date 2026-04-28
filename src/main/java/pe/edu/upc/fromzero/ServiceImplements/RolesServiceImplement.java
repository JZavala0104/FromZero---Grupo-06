package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Roles;
import pe.edu.upc.fromzero.Repositories.IRolesRepository;
import pe.edu.upc.fromzero.ServiceInterface.IRolesService;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImplement implements IRolesService {
    @Autowired
    private IRolesRepository rolesRepository;

    @Override
    public List<Roles> GetRol() {
        return rolesRepository.findAll();
    }

    @Override
    public Roles InsertRol(Roles rol) {
        return rolesRepository.save(rol);
    }

    @Override
    public void UpdateRol(Roles rol) {
        rolesRepository.save(rol);
    }

    @Override
    public void DeleteRol(int IdRol) {
        rolesRepository.deleteById(IdRol);
    }

    @Override
    public Optional<Roles> GetRolById(int IdRol) {
        return rolesRepository.findById(IdRol);
    }
}
