package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.ProyectoDesarrollador;
import pe.edu.upc.fromzero.Repositories.IProyectoDesarrolladorRepository;
import pe.edu.upc.fromzero.ServiceInterface.IProyectoDesarrolladorService;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoDesarrolladorServiceImplement implements IProyectoDesarrolladorService {
    @Autowired
    private IProyectoDesarrolladorRepository PDRepository;

    @Override
    public List<ProyectoDesarrollador> GetProyectoDesarrollador() {
        return PDRepository.findAll();
    }

    @Override
    public ProyectoDesarrollador InsertProyectoDesarrollador(ProyectoDesarrollador proyectoDesarrollador) {
        return PDRepository.save(proyectoDesarrollador);
    }

    @Override
    public void UpdateProyectoDesarrollador(ProyectoDesarrollador proyectoDesarrollador) {
        PDRepository.save(proyectoDesarrollador);
    }

    @Override
    public void DeleteProyectoDesarrollador(int IdProyDesar) {
        PDRepository.deleteById(IdProyDesar);
    }

    @Override
    public Optional<ProyectoDesarrollador> GetProyectoDesarrolladorById(int IdProyDesar) {
        return PDRepository.findById(IdProyDesar);
    }
}
