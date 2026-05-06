package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Importación necesaria
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.MensajesDTO;
import pe.edu.upc.fromzero.Entities.Mensajes;
import pe.edu.upc.fromzero.ServiceInterface.IMensajesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    @Autowired
    private IMensajesService MensajesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Soporte')")
    public ResponseEntity<?> GetMensajes() {
        ModelMapper m = new ModelMapper();
        List<MensajesDTO> listaDTO = MensajesService.GetMensaje().stream()
                .map(msg -> m.map(msg, MensajesDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay mensajes registrados");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Soporte')")
    public ResponseEntity<?> PostMensajes(@RequestBody MensajesDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El mensaje no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Mensajes msg = m.map(dto, Mensajes.class);
        Mensajes nuevo = MensajesService.InsertMensaje(msg);
        MensajesDTO nuevoDTO = m.map(nuevo, MensajesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador')")
    public ResponseEntity<?> PutMensajes(@RequestBody MensajesDTO dto) {
        Optional<Mensajes> existente = MensajesService.GetMensajeById(dto.getIdMensaje());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje no existe");
        }

        if (dto.getMensaje() == null || dto.getMensaje().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El contenido del mensaje es obligatorio");
        }

        Mensajes msg = existente.get();
        // Actualización manual de campos
        msg.setMensaje(dto.getMensaje());
        msg.setFecha(dto.getFecha());
        // Las relaciones (Proyecto/Usuario) se mantienen o se gestionan vía ModelMapper

        MensajesService.UpdateMensaje(msg);
        return ResponseEntity.ok("Mensaje actualizado");
    }

    @DeleteMapping("/Delete/{IdMensaje}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Moderador')")
    public ResponseEntity<?> DeleteMensajes(@PathVariable("IdMensaje") int IdMensaje) {
        Optional<Mensajes> existente = MensajesService.GetMensajeById(IdMensaje);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje no existe");
        }

        MensajesService.DeleteMensaje(IdMensaje);
        return ResponseEntity.ok("Mensaje eliminado");
    }
}