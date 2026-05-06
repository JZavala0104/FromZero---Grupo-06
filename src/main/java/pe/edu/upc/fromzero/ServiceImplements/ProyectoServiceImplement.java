package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Proyectos;
import pe.edu.upc.fromzero.Repositories.IProyectosRepository;
import pe.edu.upc.fromzero.ServiceInterface.IProyectosService;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoServiceImplement implements IProyectosService {
    @Autowired
    private IProyectosRepository ProyectosRepository;

    @Override
    public List<Proyectos> GetProyecto() {
        return ProyectosRepository.findAll();
    }

    @Override
    public Proyectos InsertProyecto(Proyectos proyecto) {
        return ProyectosRepository.save(proyecto);
    }

    @Override
    public void UpdateProyecto(Proyectos proyecto) {
        ProyectosRepository.save(proyecto);
    }

    @Override
    public void DeleteProyecto(int IdProyecto) {
        ProyectosRepository.deleteById(IdProyecto);
    }

    @Override
    public Optional<Proyectos> GetProyectoById(int IdProyecto) {
        return ProyectosRepository.findById(IdProyecto);
    }

    @Override
    public List<Object[]> GetQuery3() {
        return ProyectosRepository.getQuery3();
    }
}
