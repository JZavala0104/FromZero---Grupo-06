package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Empresas;
import pe.edu.upc.fromzero.Repositories.IEmpresasRepository;
import pe.edu.upc.fromzero.ServiceInterface.IEmpresasService;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresasServiceImplement implements IEmpresasService {
    @Autowired
    private IEmpresasRepository ESRepository;

    @Override
    public List<Empresas> GetEmpresa() {
        return ESRepository.findAll();
    }

    @Override
    public Empresas InsertEmpresa(Empresas empresa) {
        return ESRepository.save(empresa);
    }

    @Override
    public void UpdateEmpresa(Empresas empresa) {
        ESRepository.save(empresa);
    }

    @Override
    public void DeleteEmpresa(int IdEmpresa) {
        ESRepository.deleteById(IdEmpresa);
    }

    @Override
    public Optional<Empresas> GetEmpresaById(int IdEmpresa) {
        return ESRepository.findById(IdEmpresa);
    }
}
