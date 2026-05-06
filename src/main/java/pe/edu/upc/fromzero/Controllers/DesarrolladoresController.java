package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Importación necesaria
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

    // GET: Amplio acceso. Empresas, Gerentes y Analistas necesitan ver los perfiles para reclutar o evaluar.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Gerente', 'Analista', 'Moderador')")
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

    // POST: Solo los administradores o los propios desarrolladores al registrar su perfil.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador')")
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

    // PUT: Desarrolladores actualizando su CV/Portafolio, o moderadores corrigiendo información.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Moderador')")
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

        DesarrolladoresService.UpdateDesarrollador(d);
        return ResponseEntity.ok("Información del desarrollador actualizada");
    }

    // DELETE: Acción crítica. Mejor dejarla solo para administradores.
    @PreAuthorize("hasAuthority('Administrador')")
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