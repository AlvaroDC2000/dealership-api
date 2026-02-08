package dealership.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Dealership API Spring Boot application.
 * <p>
 * This class is responsible for bootstrapping the REST API layer of the
 * Concesionario Picasso project. It initializes the Spring application context,
 * triggers component scanning, and starts the embedded web server.
 * </p>
 * <p>
 * The API is intended to expose backend functionality to external clients,
 * such as web applications, while delegating business logic and data access
 * to the corresponding service and persistence layers.
 * </p>
 */
@SpringBootApplication
public class DealershipApiApplication {

	/**
	 * Main method used as the starting point of the Spring Boot application.
	 * <p>
	 * It delegates the application startup process to {@link SpringApplication},
	 * which handles context creation, auto-configuration, and lifecycle management.
	 * </p>
	 *
	 * @param args command-line arguments passed to the application at startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(DealershipApiApplication.class, args);
	}

}
