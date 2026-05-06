package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Valoraciones;
import pe.edu.upc.fromzero.Repositories.IValoracionesRepository;
import pe.edu.upc.fromzero.ServiceInterface.IValoracionesService;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionesServiceImplement implements IValoracionesService {
    @Autowired
    private IValoracionesRepository valoracionesRepository;

    @Override
    public List<Valoraciones> GetValoracion() {
        return valoracionesRepository.findAll();
    }

    @Override
    public Valoraciones InsertValoracion(Valoraciones valoracion) {
        return valoracionesRepository.save(valoracion);
    }

    @Override
    public void UpdateValoracion(Valoraciones valoracion) {
        valoracionesRepository.save(valoracion);
    }

    @Override
    public void DeleteValoracion(int IdValoracion) {
        valoracionesRepository.deleteById(IdValoracion);
    }

    @Override
    public Optional<Valoraciones> GetValoracionById(int IdValoracion) {
        return valoracionesRepository.findById(IdValoracion);
    }
}
