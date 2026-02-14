package pe.edu.cibertec.eureka_server_grupo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerGrupo5Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerGrupo5Application.class, args);
	}

}
