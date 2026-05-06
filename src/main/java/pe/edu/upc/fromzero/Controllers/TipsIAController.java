package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.TipsIADTO;
import pe.edu.upc.fromzero.Entities.TipsIA;
import pe.edu.upc.fromzero.ServiceInterface.ITipsIAService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/tipsia")
public class TipsIAController {

    @Autowired
    private ITipsIAService TipsIAService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Invitado', 'Soporte', 'Tester', 'Analista', 'Gerente', 'Consultor')")
    public ResponseEntity<?> GetTipsIA() {
        ModelMapper m = new ModelMapper();
        List<TipsIADTO> listaDTO = TipsIAService.GetTipsIA().stream()
                .map(t -> m.map(t, TipsIADTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tips de IA registrados");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Moderador', 'Soporte')")
    public ResponseEntity<?> PostTipsIA(@RequestBody TipsIADTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tip no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        TipsIA t = m.map(dto, TipsIA.class);
        TipsIA nuevo = TipsIAService.InsertTipsIA(t);
        TipsIADTO nuevoDTO = m.map(nuevo, TipsIADTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Moderador', 'Soporte')")
    public ResponseEntity<?> PutTipsIA(@RequestBody TipsIADTO dto) {
        Optional<TipsIA> existente = TipsIAService.GetTipsIAById(dto.getIdTip());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El tip de IA no existe");
        }

        if (dto.getContenido() == null || dto.getContenido().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El contenido del tip es obligatorio");
        }

        TipsIA t = existente.get();
        t.setContenido(dto.getContenido());
        t.setFecha(dto.getFecha());

        TipsIAService.UpdateTipsIA(t);
        return ResponseEntity.ok("Tip de IA actualizado correctamente");
    }

    @DeleteMapping("/Delete/{IdTip}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Moderador')")
    public ResponseEntity<?> DeleteTipsIA(@PathVariable("IdTip") int IdTip) {
        Optional<TipsIA> existente = TipsIAService.GetTipsIAById(IdTip);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El tip de IA no existe");
        }

        TipsIAService.DeleteTipsIA(IdTip);
        return ResponseEntity.ok("Tip de IA eliminado");
    }
}