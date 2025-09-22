package in.abhayit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
	    servers = {
	        @Server(url = "https://ticketapp.up.railway.app", description = "Railway Prod Server")
	    }
	)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
