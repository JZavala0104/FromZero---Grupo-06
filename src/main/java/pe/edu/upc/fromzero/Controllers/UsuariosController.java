package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Importación necesaria
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.UsuariosDTO;
import pe.edu.upc.fromzero.Entities.Usuarios;
import pe.edu.upc.fromzero.ServiceInterface.IUsuariosService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService UsuariosService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> GetUsuarios() {
        ModelMapper m = new ModelMapper();
        List<UsuariosDTO> usuariosDTO = UsuariosService.GetUsuario().stream()
                .map(u -> m.map(u, UsuariosDTO.class))
                .collect(Collectors.toList());

        if (usuariosDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(usuariosDTO);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> PostUsuarios(@RequestBody UsuariosDTO dto) {
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Usuarios u = m.map(dto, Usuarios.class);
        Usuarios usuario = UsuariosService.InsertUsuario(u);
        UsuariosDTO usuarioDTO = m.map(usuario, UsuariosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> PutUsuarios(@RequestBody UsuariosDTO dto) {
        Optional<Usuarios> usuarioExistente = UsuariosService.GetUsuarioById(dto.getIdUser());

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        if (dto.getNombre() == null || dto.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los datos del usuario no pueden ser nulos");
        }

        ModelMapper m = new ModelMapper();
        Usuarios u = usuarioExistente.get();

        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setFechaRegistro(dto.getFechaRegistro());

        UsuariosService.UpdateUsuario(u);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @DeleteMapping("/Delete/{IdUser}")
    @PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> DeleteUsuarios(@PathVariable("IdUser") int IdUser) {
        Optional<Usuarios> usuario = UsuariosService.GetUsuarioById(IdUser);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        UsuariosService.DeleteUsuario(IdUser);
        return ResponseEntity.ok("Usuario eliminado");
    }
}