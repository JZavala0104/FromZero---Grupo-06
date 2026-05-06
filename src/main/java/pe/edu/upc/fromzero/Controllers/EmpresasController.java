package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Importación necesaria
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.EmpresasDTO;
import pe.edu.upc.fromzero.Entities.Empresas;
import pe.edu.upc.fromzero.ServiceInterface.IEmpresasService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresasController {

    @Autowired
    private IEmpresasService EmpresasService;

    /*CRUD------------------------------------*/

    // GET: Amplio acceso. Desarrolladores y otros perfiles necesitan ver a las empresas.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Gerente', 'Analista', 'Moderador')")
    @GetMapping("/Get")
    public ResponseEntity<?> GetEmpresas() {
        ModelMapper m = new ModelMapper();
        List<EmpresasDTO> listaDTO = EmpresasService.GetEmpresa().stream()
                .map(e -> m.map(e, EmpresasDTO.class))
                .collect(Collectors.toList());

        if (listaDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay empresas registradas");
        }
        return ResponseEntity.ok(listaDTO);
    }

    // POST: Administradores o las propias empresas al crear su perfil.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa')")
    @PostMapping("/Post")
    public ResponseEntity<?> PostEmpresas(@RequestBody EmpresasDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La empresa no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        Empresas e = m.map(dto, Empresas.class);
        Empresas nueva = EmpresasService.InsertEmpresa(e);
        EmpresasDTO nuevaDTO = m.map(nueva, EmpresasDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDTO);
    }

    // PUT: Empresas actualizando su perfil o moderadores corrigiendo datos.
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa', 'Moderador')")
    @PutMapping("/Put")
    public ResponseEntity<?> PutEmpresas(@RequestBody EmpresasDTO dto) {
        Optional<Empresas> existente = EmpresasService.GetEmpresaById(dto.getIdEmpresa());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa no existe");
        }

        if (dto.getNombreEmpresa() == null || dto.getDescripcion() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre y la descripción son obligatorios");
        }

        Empresas e = existente.get();
        e.setNombreEmpresa(dto.getNombreEmpresa());
        e.setDescripcion(dto.getDescripcion());

        EmpresasService.UpdateEmpresa(e);
        return ResponseEntity.ok("Información de la empresa actualizada");
    }

    // DELETE: Acción crítica, reservada para el administrador.
    @PreAuthorize("hasAuthority('Administrador')")
    @DeleteMapping("/Delete/{IdEmpresa}")
    public ResponseEntity<?> DeleteEmpresas(@PathVariable("IdEmpresa") int IdEmpresa) {
        Optional<Empresas> existente = EmpresasService.GetEmpresaById(IdEmpresa);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La empresa no existe");
        }

        EmpresasService.DeleteEmpresa(IdEmpresa);
        return ResponseEntity.ok("Empresa eliminada");
    }
}