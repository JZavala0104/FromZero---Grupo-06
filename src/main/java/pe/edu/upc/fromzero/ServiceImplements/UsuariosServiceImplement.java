package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.Entities.Usuarios;
import pe.edu.upc.fromzero.Repositories.IUsuariosRepository;
import pe.edu.upc.fromzero.ServiceInterface.IUsuariosService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuariosServiceImplement implements IUsuariosService, UserDetailsService {
    @Autowired
    private IUsuariosRepository usuariosRepository;

    @Override
    public List<Usuarios> GetUsuario() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios InsertUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public void UpdateUsuario(Usuarios usuario) {
        usuariosRepository.save(usuario);
    }

    @Override
    public void DeleteUsuario(int IdUsuario) {
        usuariosRepository.deleteById(IdUsuario);
    }

    @Override
    public Optional<Usuarios> GetUsuarioById(int IdUsuario) {
        return usuariosRepository.findById(IdUsuario);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios user = usuariosRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User not exists: %s", username));
        }

        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(user.getIdRol().getNombre()));

        UserDetails ud = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // Recuerda: Esto debe ser el hash (Bcrypt), no texto plano
                user.isHabilitado(),
                true,
                true,
                true,
                roles
        );

        return ud;
    }
}
