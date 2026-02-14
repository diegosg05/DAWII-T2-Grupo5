package cibertec.edu.pe.api_security.controller;

import cibertec.edu.pe.api_security.dto.Login;
import cibertec.edu.pe.api_security.dto.Registrarse;
import cibertec.edu.pe.api_security.dto.UsuarioSecurity;
import cibertec.edu.pe.api_security.model.Rol;
import cibertec.edu.pe.api_security.model.Usuario;
import cibertec.edu.pe.api_security.repository.RolRepository;
import cibertec.edu.pe.api_security.security.IJwtService;
import cibertec.edu.pe.api_security.service.DetalleUsuarioService;
import cibertec.edu.pe.api_security.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final UsuarioService usuarioService;
    private final DetalleUsuarioService detalleUsuarioService;
    private final IJwtService jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    public AuthController(UsuarioService usuarioService, DetalleUsuarioService detalleUsuarioService,
                          IJwtService jwtService, AuthenticationManager authManager,
                          PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.usuarioService = usuarioService;
        this.detalleUsuarioService = detalleUsuarioService;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioSecurity> login(
            @RequestBody Login login) {
        UsuarioSecurity userSecurity = new UsuarioSecurity();
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getNomusuario(),
                            login.getPassword()));
            Usuario usuario = usuarioService.obtenerUsuarioPorNomusuario(
                    login.getNomusuario());
            String token = jwtService.generarToken(usuario,
                    detalleUsuarioService.obtenerAutorizacionesPorRol(
                            usuario.getRol()));
            userSecurity.setIdUsuario(usuario.getId());
            userSecurity.setNomUsuario(usuario.getNomusuario());
            userSecurity.setToken(token);
            return ResponseEntity.ok(userSecurity);
        } catch (AuthenticationException e) {
            userSecurity.setMensajeError("Nombre de usuario y/o la contraseña es incorrecta");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(userSecurity);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registrase(@RequestBody Registrarse registrarse) {

        // Buscar el rol
        Rol rol = rolRepository.findById(registrarse.getRol()).orElseThrow(() -> new RuntimeException("Rol no fue encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNombres("Usuario Nombres");
        usuario.setApellidos("Usuario Apellidos");
        usuario.setNomusuario(registrarse.getNomusuario());
        usuario.setPassword(passwordEncoder.encode(registrarse.getPassword()));


        usuario.setRol(rol);

        usuarioService.saveUsuario(usuario);

        return ResponseEntity.noContent().build();

    }

}
