package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.CodigoGenerado;
import pe.edu.upc.fromzero.Repositories.ICodigoGeneradoRepository;
import pe.edu.upc.fromzero.ServiceInterface.ICodigoGeneradoService;

import java.util.List;
import java.util.Optional;

@Service
public class CodigoGeneradoServiceImplement implements ICodigoGeneradoService {
    @Autowired
    private ICodigoGeneradoRepository CGRepository;

    @Override
    public List<CodigoGenerado> GetCodigoGenerado() {
        return CGRepository.findAll();
    }

    @Override
    public CodigoGenerado InsertCodigoGenerado(CodigoGenerado codigoGenerado) {
        return CGRepository.save(codigoGenerado);
    }

    @Override
    public void UpdateCodigoGenerado(CodigoGenerado codigoGenerado) {
        CGRepository.save(codigoGenerado);
    }

    @Override
    public void DeleteCodigoGenerado(int idCodigoGenerado) {
        CGRepository.deleteById(idCodigoGenerado);
    }

    @Override
    public Optional<CodigoGenerado> GetCodigoGeneradoById(int idCodigoGenerado) {
        return CGRepository.findById(idCodigoGenerado);
    }
}
