package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Tareas;
import pe.edu.upc.fromzero.Repositories.ITareasRepository;
import pe.edu.upc.fromzero.ServiceInterface.ITareasService;

import java.util.List;
import java.util.Optional;

@Service
public class TareasServiceImplement implements ITareasService {
    @Autowired
    private ITareasRepository tareasRepository;

    @Override
    public List<Tareas> GetTarea() {
        return tareasRepository.findAll();
    }

    @Override
    public Tareas InsertTarea(Tareas tarea) {
        return tareasRepository.save(tarea);
    }

    @Override
    public void UpdateTarea(Tareas tarea) {
        tareasRepository.save(tarea);
    }

    @Override
    public void DeleteTarea(int IdTarea) {
        tareasRepository.deleteById(IdTarea);
    }

    @Override
    public Optional<Tareas> GetTareaById(int IdTarea) {
        return tareasRepository.findById(IdTarea);
    }

    @Override
    public List<Object[]> GetQuery4() {
        return tareasRepository.getQuery4();
    }
}
