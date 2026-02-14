package cibertec.edu.pe.api_security.repository;

import cibertec.edu.pe.api_security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNomusuario(String nomusuario);

}
