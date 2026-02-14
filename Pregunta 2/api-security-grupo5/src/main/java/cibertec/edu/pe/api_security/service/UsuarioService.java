package cibertec.edu.pe.api_security.service;

import cibertec.edu.pe.api_security.model.Usuario;
import cibertec.edu.pe.api_security.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario obtenerUsuarioPorNomusuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }


}
