package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.ValoracionesDTO;
import pe.edu.upc.fromzero.Entities.Valoraciones;
import pe.edu.upc.fromzero.ServiceInterface.IValoracionesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionesController {

    @Autowired
    private IValoracionesService ValoracionesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetValoraciones() {
        ModelMapper m = new ModelMapper();
        List<ValoracionesDTO> listaDTO = ValoracionesService.GetValoracion().stream()
                .map(v -> m.map(v, ValoracionesDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay valoraciones registradas");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostValoraciones(@RequestBody ValoracionesDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La valoración no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        Valoraciones v = m.map(dto, Valoraciones.class);
        Valoraciones nueva = ValoracionesService.InsertValoracion(v);
        ValoracionesDTO nuevaDTO = m.map(nueva, ValoracionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutValoraciones(@RequestBody ValoracionesDTO dto) {
        Optional<Valoraciones> existente = ValoracionesService.GetValoracionById(dto.getIdValoracion());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La valoración no existe");
        }

        if (dto.getPuntuacion() < 0 || dto.getComentario() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La puntuación y el comentario son obligatorios");
        }

        Valoraciones v = existente.get();
        // Actualización de campos
        v.setPuntuacion(dto.getPuntuacion());
        v.setComentario(dto.getComentario());
        // El IdProyecto se actualiza mediante ModelMapper si está presente en el DTO

        ValoracionesService.UpdateValoracion(v);
        return ResponseEntity.ok("Valoración actualizada correctamente");
    }

    @DeleteMapping("/Delete/{IdValoracion}")
    public ResponseEntity<?> DeleteValoraciones(@PathVariable("IdValoracion") int IdValoracion) {
        Optional<Valoraciones> existente = ValoracionesService.GetValoracionById(IdValoracion);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La valoración no existe");
        }

        ValoracionesService.DeleteValoracion(IdValoracion);
        return ResponseEntity.ok("Valoración eliminada");
    }
}