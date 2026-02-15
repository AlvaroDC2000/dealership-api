package dealership.api.controller;

import dealership.api.dao.AuthDao;
import dealership.api.model.LoginRequest;
import dealership.api.model.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Authentication controller.
 *
 * <p>
 * Exposes authentication-related endpoints for the application.
 * This controller is responsible for validating user credentials
 * and returning basic session information required by the UI layer.
 * </p>
 *
 * <p>
 * Level 1 scope:
 * <ul>
 *     <li>Validates username and password.</li>
 *     <li>Returns session information without issuing JWT tokens.</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthDao authDao;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Creates a new {@code AuthController}.
     *
     * @param authDao Data access object used to retrieve authentication-related user data.
     */
    public AuthController(AuthDao authDao) {
        this.authDao = authDao;
    }

    /**
     * Authenticates a user using the provided credentials.
     *
     * <p>
     * The method performs basic validation to ensure that username and password
     * are provided. It then retrieves the user by username and verifies the
     * password using BCrypt hashing.
     * </p>
     *
     * <p>
     * If authentication succeeds, a {@link LoginResponse} containing session
     * information is returned. If authentication fails, a generic unauthorized
     * response is sent without exposing sensitive details.
     * </p>
     *
     * @param req The login request containing username and password.
     * @return A {@link ResponseEntity} containing either:
     *         <ul>
     *             <li>A {@link LoginResponse} with session data (HTTP 200).</li>
     *             <li>An error message with HTTP 400 if input is invalid.</li>
     *             <li>An error message with HTTP 401 if credentials are invalid.</li>
     *         </ul>
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        if (req.getUsername() == null || req.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        if (req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password is required"));
        }

        String username = req.getUsername().trim();

        var optUser = authDao.findLoginUserByUsername(username);
        if (optUser.isEmpty()) {
            // Keep it generic
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        var user = optUser.get();

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        LoginResponse resp = new LoginResponse(
                user.getId(),
                user.getDealershipId(),
                user.getRoleName(),
                user.getUsername(),
                user.getFullName()
        );

        return ResponseEntity.ok(resp);
    }
    
    /**
     * Simple health-check endpoint.
     *
     * <p>
     * Used to verify that the authentication service is up and reachable.
     * This endpoint does not require authentication and simply returns a
     * basic status response.
     * </p>
     *
     * @return A map containing a status indicator.
     */
    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "ok");
    }

    /**
     * Generates a BCrypt hash for a predefined test password.
     *
     * <p>
     * This endpoint is intended for development or testing purposes,
     * allowing quick generation of a hashed password value.
     * </p>
     *
     * @return A BCrypt-encoded hash of the sample password.
     */
    @GetMapping("/hash-test")
    public String hashTest() {
        return new BCryptPasswordEncoder().encode("owner123");
    }

}

