package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.TareasDTO;
import pe.edu.upc.fromzero.Entities.Tareas;
import pe.edu.upc.fromzero.ServiceInterface.ITareasService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {

    @Autowired
    private ITareasService TareasService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetTareas() {
        ModelMapper m = new ModelMapper();
        List<TareasDTO> listaDTO = TareasService.GetTarea().stream()
                .map(t -> m.map(t, TareasDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tareas registradas");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostTareas(@RequestBody TareasDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La tarea no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        Tareas t = m.map(dto, Tareas.class);
        Tareas nueva = TareasService.InsertTarea(t);
        TareasDTO nuevaDTO = m.map(nueva, TareasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutTareas(@RequestBody TareasDTO dto) {
        Optional<Tareas> existente = TareasService.GetTareaById(dto.getIdTarea());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no existe");
        }

        if (dto.getTitulo() == null || dto.getEstado() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El título y el estado son obligatorios");
        }

        Tareas t = existente.get();
        // Actualización manual de campos
        t.setTitulo(dto.getTitulo());
        t.setDescripcion(dto.getDescripcion());
        t.setFechaLimite(dto.getFechaLimite());
        t.setEstado(dto.getEstado());
        // El IdProyecto se gestiona usualmente vía ModelMapper basándose en el DTO

        TareasService.UpdateTarea(t);
        return ResponseEntity.ok("Tarea actualizada correctamente");
    }

    @DeleteMapping("/Delete/{IdTarea}")
    public ResponseEntity<?> DeleteTareas(@PathVariable("IdTarea") int IdTarea) {
        Optional<Tareas> existente = TareasService.GetTareaById(IdTarea);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La tarea no existe");
        }

        TareasService.DeleteTarea(IdTarea);
        return ResponseEntity.ok("Tarea eliminada");
    }
}