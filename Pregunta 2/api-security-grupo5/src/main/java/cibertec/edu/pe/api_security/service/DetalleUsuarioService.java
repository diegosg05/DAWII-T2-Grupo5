package cibertec.edu.pe.api_security.service;

import cibertec.edu.pe.api_security.model.Rol;
import cibertec.edu.pe.api_security.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public DetalleUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerUsuarioPorNomusuario(username);
        return obtenerUsuarioSpringSecurity(usuario,
                obtenerAutorizacionesPorRol(usuario.getRol()));
    }

    public List<GrantedAuthority> obtenerAutorizacionesPorRol(
            Rol rol) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + rol.getNomrol()));
        
        return authorities;
    }

    public UserDetails obtenerUsuarioSpringSecurity(Usuario usuario,
                                                    List<GrantedAuthority> authorities) {
        return new User(usuario.getNomusuario(),
                usuario.getPassword(), true,
                true, true, true, authorities);
    }

}
