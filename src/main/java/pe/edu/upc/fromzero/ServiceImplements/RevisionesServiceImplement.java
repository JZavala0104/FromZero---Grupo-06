package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Revisiones;
import pe.edu.upc.fromzero.Repositories.IRevisionesRepository;
import pe.edu.upc.fromzero.ServiceInterface.IRevisionesService;

import java.util.List;
import java.util.Optional;

@Service
public class RevisionesServiceImplement implements IRevisionesService {
    @Autowired
    private IRevisionesRepository revisionesRepository;

    @Override
    public List<Revisiones> GetRevision() {
        return revisionesRepository.findAll();
    }

    @Override
    public Revisiones InsertRevision(Revisiones revision) {
        return revisionesRepository.save(revision);
    }

    @Override
    public void UpdateRevision(Revisiones revision) {
        revisionesRepository.save(revision);
    }

    @Override
    public void DeleteRevision(int IdRevision) {
        revisionesRepository.deleteById(IdRevision);
    }

    @Override
    public Optional<Revisiones> GetRevisionById(int IdRevision) {
        return revisionesRepository.findById(IdRevision);
    }
}
