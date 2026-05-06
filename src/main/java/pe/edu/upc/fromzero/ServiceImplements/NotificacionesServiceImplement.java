package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Notificaciones;
import pe.edu.upc.fromzero.Repositories.INotificacionesRepository;
import pe.edu.upc.fromzero.ServiceInterface.INotificacionesService;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesServiceImplement implements INotificacionesService {
    @Autowired
    private INotificacionesRepository NSRepository;

    @Override
    public List<Notificaciones> GetNotificacion() {
        return NSRepository.findAll();
    }

    @Override
    public Notificaciones InsertNotificacion(Notificaciones notificacion) {
        return NSRepository.save(notificacion);
    }

    @Override
    public void UpdateNotificacion(Notificaciones notificacion) {
        NSRepository.save(notificacion);
    }

    @Override
    public void DeleteNotificacion(int IdNotificacion) {
        NSRepository.deleteById(IdNotificacion);
    }

    @Override
    public Optional<Notificaciones> GetNotificacionById(int IdNotificacion) {
        return NSRepository.findById(IdNotificacion);
    }

    @Override
    public List<Object[]> GetQuery5() {
        return NSRepository.getQuery5();
    }

}
