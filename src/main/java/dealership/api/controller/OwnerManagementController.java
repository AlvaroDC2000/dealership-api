package dealership.api.controller;

import dealership.api.dao.DealershipDao;
import dealership.api.dao.OwnerUserDao;
import dealership.api.dao.RoleDao;
import dealership.api.model.CreateUserRequest;
import dealership.api.model.IdNameRow;
import dealership.api.model.UserRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller responsible for owner-level user and reference data management.
 * <p>
 * This controller exposes endpoints that allow the owner to retrieve reference
 * information such as roles and dealerships, as well as manage employee accounts
 * including listing and creation of users.
 * </p>
 */
@RestController
@RequestMapping("/api/owner")
public class OwnerManagementController {

    private final RoleDao roleDao;
    private final DealershipDao dealershipDao;
    private final OwnerUserDao ownerUserDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Creates a new controller instance with the required data access dependencies.
     * <p>
     * Dependencies are injected through the constructor and are used to retrieve
     * reference data and manage user-related persistence operations.
     * </p>
     *
     * @param roleDao data access object for role queries
     * @param dealershipDao data access object for dealership queries
     * @param ownerUserDao data access object for owner-level user management
     */
    public OwnerManagementController(RoleDao roleDao, DealershipDao dealershipDao, OwnerUserDao ownerUserDao) {
        this.roleDao = roleDao;
        this.dealershipDao = dealershipDao;
        this.ownerUserDao = ownerUserDao;
    }

    /**
     * Retrieves the list of available roles.
     * <p>
     * This endpoint returns a simplified list of role identifiers and names,
     * typically used to populate selection controls in management interfaces.
     * </p>
     *
     * @return a list of role identifier and name pairs
     */
    @GetMapping("/roles")
    public List<IdNameRow> getRoles() {
        return roleDao.findAllRoles();
    }

    /**
     * Retrieves the list of available dealerships.
     * <p>
     * The returned data contains basic identifier and name information for each
     * dealership and is intended for filtering and selection purposes.
     * </p>
     *
     * @return a list of dealership identifier and name pairs
     */
    @GetMapping("/dealerships")
    public List<IdNameRow> getDealerships() {
        return dealershipDao.findAllDealerships();
    }

    /**
     * Retrieves the list of employees with optional filtering criteria.
     * <p>
     * The result can be filtered by dealership, role, and active status by
     * providing the corresponding request parameters.
     * </p>
     *
     * @param dealershipId optional dealership identifier to filter users
     * @param roleId optional role identifier to filter users
     * @param active optional flag indicating whether users are active
     * @return a list of users matching the provided filter criteria
     */
    @GetMapping("/users")
    public List<UserRow> getUsers(
            @RequestParam(required = false) Integer dealershipId,
            @RequestParam(required = false) Integer roleId,
            @RequestParam(required = false) Boolean active
    ) {
        return ownerUserDao.findUsers(dealershipId, roleId, active);
    }

    /**
     * Creates a new employee user.
     * <p>
     * This endpoint performs basic request validation, enforces username uniqueness,
     * encodes the provided password using BCrypt, and persists the new user using
     * the owner-level user DAO.
     * </p>
     *
     * <p>
     * IMPORTANT:
     * Responses are returned as JSON (Map) to keep the API consistent and
     * avoid client-side JSON parsing issues.
     * </p>
     *
     * @param req request object containing the user data to be created
     * @return an HTTP response containing either:
     *         <ul>
     *             <li>HTTP 201 with the created user identifier and a success message.</li>
     *             <li>HTTP 400 with a validation error message.</li>
     *             <li>HTTP 409 if the username already exists.</li>
     *         </ul>
     */
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody CreateUserRequest req) {

        // Validate required numeric fields
        if (req.getDealershipId() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Dealership is required"));
        }
        if (req.getRoleId() <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Role is required"));
        }

        // Validate required strings
        if (req.getUsername() == null || req.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username is required"));
        }
        if (req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password is required"));
        }
        if (req.getFullName() == null || req.getFullName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Full name is required"));
        }

        String username = req.getUsername().trim();
        req.setUsername(username);

        // For the web, default to active = true if the client did not send it explicitly.
        // This keeps the create form simple.
        if (!req.isActive()) {
            req.setActive(true);
        }

        // Validate username uniqueness
        if (ownerUserDao.existsUsername(username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Username already exists"));
        }

        // Encode password (BCrypt)
        String hash = passwordEncoder.encode(req.getPassword());

        int newId = ownerUserDao.insertUser(req, hash);

        // Return JSON (not plain text) to avoid Angular "not valid JSON" errors
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", newId,
                "message", "User created successfully"
        ));
    }
}
