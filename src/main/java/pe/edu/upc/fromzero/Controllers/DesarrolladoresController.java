package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.DesarrolladoresDTO;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import pe.edu.upc.fromzero.ServiceInterface.IDesarrolladoresService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/desarrolladores")
public class DesarrolladoresController {

    @Autowired
    private IDesarrolladoresService DesarrolladoresService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetDesarrolladores() {
        ModelMapper m = new ModelMapper();
        List<DesarrolladoresDTO> listaDTO = DesarrolladoresService.GetDesarrollador().stream()
                .map(d -> m.map(d, DesarrolladoresDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay desarrolladores registrados");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostDesarrolladores(@RequestBody DesarrolladoresDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El desarrollador no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Desarrolladores d = m.map(dto, Desarrolladores.class);
        Desarrolladores nuevo = DesarrolladoresService.InsertDesarrollador(d);
        DesarrolladoresDTO nuevoDTO = m.map(nuevo, DesarrolladoresDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutDesarrolladores(@RequestBody DesarrolladoresDTO dto) {
        Optional<Desarrolladores> existente = DesarrolladoresService.GetDesarrolladorById(dto.getIdDesarrollador());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El desarrollador no existe");
        }

        if (dto.getHabilidades() == null || dto.getPortafolio() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Habilidades y portafolio son obligatorios");
        }

        Desarrolladores d = existente.get();
        // Actualización manual de campos según tu lógica
        d.setHabilidades(dto.getHabilidades());
        d.setExperiencia(dto.getExperiencia());
        d.setPortafolio(dto.getPortafolio());
        // El IdUser se mantiene vinculado a la entidad original o se mapea vía ModelMapper

        DesarrolladoresService.UpdateDesarrollador(d);
        return ResponseEntity.ok("Información del desarrollador actualizada");
    }

    @DeleteMapping("/Delete/{IdDesarrollador}")
    public ResponseEntity<?> DeleteDesarrolladores(@PathVariable("IdDesarrollador") int IdDesarrollador) {
        Optional<Desarrolladores> existente = DesarrolladoresService.GetDesarrolladorById(IdDesarrollador);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El desarrollador no existe");
        }

        DesarrolladoresService.DeleteDesarrollador(IdDesarrollador);
        return ResponseEntity.ok("Desarrollador eliminado");
    }
}