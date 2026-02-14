package cibertec.edu.pe.api_security.security;

import cibertec.edu.pe.api_security.service.DetalleUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final DetalleUsuarioService usuarioService;

    public SecurityConfig(DetalleUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            IJwtService jwtService) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/api/v1/productos/**", "/api/v1/empleados/**")
                                .hasAnyRole("JEFE", "ADMIN")
                                .requestMatchers("/api/v1/productos/**", "/api/v1/empleados/**")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login", "/api/v1/auth/registro")
                                .permitAll()
                ).authenticationProvider(daoAuth())
                .addFilterBefore(new FiltroJwtAuth(jwtService),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    AuthenticationProvider daoAuth() {
        DaoAuthenticationProvider dao =
                new DaoAuthenticationProvider(usuarioService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
