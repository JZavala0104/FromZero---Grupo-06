package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.CodigoGeneradoDTO;
import pe.edu.upc.fromzero.Entities.CodigoGenerado;
import pe.edu.upc.fromzero.ServiceInterface.ICodigoGeneradoService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/codigogenerado")
public class CodigoGeneradoController {

    @Autowired
    private ICodigoGeneradoService CodigoGeneradoService;

    /*CRUD------------------------------------*/
    @GetMapping("/Get")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Tester', 'Analista', 'Soporte', 'Gerente', 'Consultor')")
    public ResponseEntity<?> GetCodigoGenerado() {
        ModelMapper m = new ModelMapper();
        List<CodigoGeneradoDTO> listaDTO = CodigoGeneradoService.GetCodigoGenerado().stream()
                .map(c -> m.map(c, CodigoGeneradoDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay códigos generados registrados");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa')")
    public ResponseEntity<?> PostCodigoGenerado(@RequestBody CodigoGeneradoDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El código generado no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        CodigoGenerado c = m.map(dto, CodigoGenerado.class);
        CodigoGenerado nuevo = CodigoGeneradoService.InsertCodigoGenerado(c);
        CodigoGeneradoDTO nuevoDTO = m.map(nuevo, CodigoGeneradoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Moderador')")
    public ResponseEntity<?> PutCodigoGenerado(@RequestBody CodigoGeneradoDTO dto) {
        Optional<CodigoGenerado> existente = CodigoGeneradoService.GetCodigoGeneradoById(dto.getIdCode());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de código no existe");
        }

        if (dto.getPrompt() == null || dto.getCodigo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El prompt y el código no pueden ser nulos");
        }

        CodigoGenerado c = existente.get();

        c.setPrompt(dto.getPrompt());
        c.setCodigo(dto.getCodigo());
        c.setLenguaje(dto.getLenguaje());
        c.setFecha(dto.getFecha());

        CodigoGeneradoService.UpdateCodigoGenerado(c);
        return ResponseEntity.ok("Registro de código actualizado");
    }

    @DeleteMapping("/Delete/{IdCode}")
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<?> DeleteCodigoGenerado(@PathVariable("IdCode") int IdCode) {
        Optional<CodigoGenerado> existente = CodigoGeneradoService.GetCodigoGeneradoById(IdCode);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El registro de código no existe");
        }

        CodigoGeneradoService.DeleteCodigoGenerado(IdCode);
        return ResponseEntity.ok("Registro de código eliminado");
    }
}