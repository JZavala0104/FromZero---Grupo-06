package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.ProyectoDesarrolladorDTO;
import pe.edu.upc.fromzero.Entities.ProyectoDesarrollador;
import pe.edu.upc.fromzero.ServiceInterface.IProyectoDesarrolladorService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/proyectodesarrollador")
public class ProyectoDesarrolladorController {

    @Autowired
    private IProyectoDesarrolladorService ProyectoDesarrolladorService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetProyectoDesarrollador() {
        ModelMapper m = new ModelMapper();
        List<ProyectoDesarrolladorDTO> listaDTO = ProyectoDesarrolladorService.GetProyectoDesarrollador().stream()
                .map(pd -> m.map(pd, ProyectoDesarrolladorDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay asignaciones de proyectos a desarrolladores");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostProyectoDesarrollador(@RequestBody ProyectoDesarrolladorDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La asignación no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        ProyectoDesarrollador pd = m.map(dto, ProyectoDesarrollador.class);
        ProyectoDesarrollador nuevo = ProyectoDesarrolladorService.InsertProyectoDesarrollador(pd);
        ProyectoDesarrolladorDTO nuevoDTO = m.map(nuevo, ProyectoDesarrolladorDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutProyectoDesarrollador(@RequestBody ProyectoDesarrolladorDTO dto) {
        Optional<ProyectoDesarrollador> existente = ProyectoDesarrolladorService.GetProyectoDesarrolladorById(dto.getIdProyDesar());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignación no existe");
        }

        ProyectoDesarrollador pd = existente.get();

        ModelMapper m = new ModelMapper();
        m.map(dto, pd);

        ProyectoDesarrolladorService.UpdateProyectoDesarrollador(pd);
        return ResponseEntity.ok("Asignación actualizada correctamente");
    }

    @DeleteMapping("/Delete/{IdProyDesar}")
    public ResponseEntity<?> DeleteProyectoDesarrollador(@PathVariable("IdProyDesar") int IdProyDesar) {
        Optional<ProyectoDesarrollador> existente = ProyectoDesarrolladorService.GetProyectoDesarrolladorById(IdProyDesar);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignación no existe");
        }

        ProyectoDesarrolladorService.DeleteProyectoDesarrollador(IdProyDesar);
        return ResponseEntity.ok("Asignación eliminada");
    }
}