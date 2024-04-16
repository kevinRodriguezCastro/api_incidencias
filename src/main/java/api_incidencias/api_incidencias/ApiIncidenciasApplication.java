package api_incidencias.api_incidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("api_incidencias.api_incidencias.Servicios.Seguridad")
public class ApiIncidenciasApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiIncidenciasApplication.class, args);
	}

}
