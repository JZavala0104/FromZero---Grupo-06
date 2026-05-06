package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Mensajes;
import pe.edu.upc.fromzero.Repositories.IMensajesRepository;
import pe.edu.upc.fromzero.ServiceInterface.IMensajesService;
import java.util.List;
import java.util.Optional;

@Service
public class MensajesServiceImplement implements IMensajesService {
    @Autowired
    private IMensajesRepository MSRepository;

    @Override
    public List<Mensajes> GetMensaje() {
        return MSRepository.findAll();
    }

    @Override
    public Mensajes InsertMensaje(Mensajes mensaje) {
        return MSRepository.save(mensaje);
    }

    @Override
    public void UpdateMensaje(Mensajes mensaje) {
        MSRepository.save(mensaje);
    }

    @Override
    public void DeleteMensaje(int IdMensaje) {
        MSRepository.deleteById(IdMensaje);
    }

    @Override
    public Optional<Mensajes> GetMensajeById(int IdMensaje) {
        return MSRepository.findById(IdMensaje);
    }
}
