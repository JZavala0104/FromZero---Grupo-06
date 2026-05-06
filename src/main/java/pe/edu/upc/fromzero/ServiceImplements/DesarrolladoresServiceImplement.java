package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import pe.edu.upc.fromzero.Repositories.IDesarrolladoresRepository;
import pe.edu.upc.fromzero.ServiceInterface.IDesarrolladoresService;

import java.util.List;
import java.util.Optional;

@Service
public class DesarrolladoresServiceImplement implements IDesarrolladoresService {
    @Autowired
    private IDesarrolladoresRepository DSRepository;

    @Override
    public List<Desarrolladores> GetDesarrollador() {
        return DSRepository.findAll();
    }

    @Override
    public Desarrolladores InsertDesarrollador(Desarrolladores desarrollador) {
        return DSRepository.save(desarrollador);
    }

    @Override
    public void UpdateDesarrollador(Desarrolladores desarrollador) {
        DSRepository.save(desarrollador);
    }

    @Override
    public void DeleteDesarrollador(int IdDesarrollador) {
        DSRepository.deleteById(IdDesarrollador);
    }

    @Override
    public Optional<Desarrolladores> GetDesarrolladorById(int IdDesarrollador) {
        return DSRepository.findById(IdDesarrollador);
    }

    @Override
    public List<Object[]> GetQuery2() {
        return DSRepository.getQuery2();
    }
}
