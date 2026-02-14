package cibertec.edu.pe.api_security.security;

import cibertec.edu.pe.api_security.model.Usuario;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IJwtService {

    String generarToken(Usuario usuario,
                        List<GrantedAuthority> authorities);
    Claims obtenerClaims(String token);
    boolean tokenValido(String token);
    String extraerToken(HttpServletRequest request);
    void generarAutenticacion(Claims claims);

}
