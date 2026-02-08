package dealership.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller used to expose a basic health check endpoint.
 * <p>
 * This controller provides a simple way to verify that the API is running
 * and reachable. It is typically used for monitoring, diagnostics, or
 * deployment validation purposes.
 * </p>
 */
@RestController
public class HealthController {

    /**
     * Health check endpoint.
     * <p>
     * When invoked, this endpoint returns a fixed response indicating that
     * the application is up and responding to HTTP requests.
     * </p>
     *
     * @return a plain text response confirming the API is operational
     */
    @GetMapping("/api/health")
    public String health() {
        return "ok";
    }
}

