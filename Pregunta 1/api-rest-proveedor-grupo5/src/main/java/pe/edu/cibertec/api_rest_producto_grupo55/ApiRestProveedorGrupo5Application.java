package pe.edu.cibertec.api_rest_producto_grupo55;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiRestProveedorGrupo5Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestProveedorGrupo5Application.class, args);
	}

}
