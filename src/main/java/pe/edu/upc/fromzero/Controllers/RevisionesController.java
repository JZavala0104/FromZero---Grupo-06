package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.RevisionesDTO;
import pe.edu.upc.fromzero.Entities.Revisiones;
import pe.edu.upc.fromzero.ServiceInterface.IRevisionesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/revisiones")
public class RevisionesController {

    @Autowired
    private IRevisionesService RevisionesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetRevisiones() {
        ModelMapper m = new ModelMapper();
        List<RevisionesDTO> listaDTO = RevisionesService.GetRevision().stream()
                .map(r -> m.map(r, RevisionesDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay revisiones registradas");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostRevisiones(@RequestBody RevisionesDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La revisión no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        Revisiones r = m.map(dto, Revisiones.class);
        Revisiones nueva = RevisionesService.InsertRevision(r);
        RevisionesDTO nuevaDTO = m.map(nueva, RevisionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutRevisiones(@RequestBody RevisionesDTO dto) {
        Optional<Revisiones> existente = RevisionesService.GetRevisionById(dto.getIdRevision());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La revisión no existe");
        }

        if (dto.getComentar() == null || dto.getEstado() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El comentario y el estado son obligatorios");
        }

        Revisiones r = existente.get();
        // Actualización de campos manual según tu estructura
        r.setComentar(dto.getComentar());
        r.setEstado(dto.getEstado());
        r.setFecha(dto.getFecha());
        // El vínculo con Tareas se gestiona usualmente por ModelMapper a través del ID en el DTO

        RevisionesService.UpdateRevision(r);
        return ResponseEntity.ok("Revisión actualizada correctamente");
    }

    @DeleteMapping("/Delete/{IdRevision}")
    public ResponseEntity<?> DeleteRevisiones(@PathVariable("IdRevision") int IdRevision) {
        Optional<Revisiones> existente = RevisionesService.GetRevisionById(IdRevision);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La revisión no existe");
        }

        RevisionesService.DeleteRevision(IdRevision);
        return ResponseEntity.ok("Revisión eliminada");
    }
}