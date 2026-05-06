package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.ProyectosDTO;
import pe.edu.upc.fromzero.Entities.Proyectos;
import pe.edu.upc.fromzero.ServiceInterface.IProyectosService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectosController {

    @Autowired
    private IProyectosService ProyectosService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Soporte', 'Gerente', 'Analista', 'Consultor')")
    public ResponseEntity<?> GetProyectos() {
        ModelMapper m = new ModelMapper();
        List<ProyectosDTO> listaDTO = ProyectosService.GetProyecto().stream()
                .map(p -> m.map(p, ProyectosDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos registrados");
        }
        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa')")
    public ResponseEntity<?> PostProyectos(@RequestBody ProyectosDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El proyecto no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Proyectos p = m.map(dto, Proyectos.class);
        Proyectos nuevo = ProyectosService.InsertProyecto(p);
        ProyectosDTO nuevoDTO = m.map(nuevo, ProyectosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa', 'Moderador')")
    public ResponseEntity<?> PutProyectos(@RequestBody ProyectosDTO dto) {
        Optional<Proyectos> existente = ProyectosService.GetProyectoById(dto.getIdProject());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto no existe");
        }

        if (dto.getTitulo() == null || dto.getPresupuesto() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos del proyecto inválidos");
        }

        Proyectos p = existente.get();
        // Actualización de campos
        p.setTitulo(dto.getTitulo());
        p.setDescripcion(dto.getDescripcion());
        p.setPresupuesto(dto.getPresupuesto());
        p.setEstado(dto.getEstado());
        p.setFechaInicio(dto.getFechaInicio());
        p.setFechaFin(dto.getFechaFin());

        ProyectosService.UpdateProyecto(p);
        return ResponseEntity.ok("Proyecto actualizado correctamente");
    }

    @DeleteMapping("/Delete/{IdProject}")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa')")
    public ResponseEntity<?> DeleteProyectos(@PathVariable("IdProject") int IdProject) {
        Optional<Proyectos> existente = ProyectosService.GetProyectoById(IdProject);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El proyecto no existe");
        }

        ProyectosService.DeleteProyecto(IdProject);
        return ResponseEntity.ok("Proyecto eliminado");
    }
}