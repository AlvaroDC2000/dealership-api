package dealership.api.controller;

import dealership.api.dao.DealershipDao;
import dealership.api.dao.OwnerUserDao;
import dealership.api.dao.RoleDao;
import dealership.api.model.CreateUserRequest;
import dealership.api.model.IdNameRow;
import dealership.api.model.UserRow;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * This endpoint validates the incoming request data, checks for username
     * uniqueness, encodes the provided password, and persists the new user.
     * </p>
     *
     * @param req request object containing the user data to be created
     * @return an HTTP response indicating success or validation errors
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest req) {

        if (req.getDealershipId() <= 0 || req.getRoleId() <= 0) {
            return ResponseEntity.badRequest().body("dealershipId y roleId son obligatorios");
        }
        if (req.getUsername() == null || req.getUsername().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("username es obligatorio");
        }
        if (req.getPassword() == null || req.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("password es obligatorio");
        }
        if (req.getFullName() == null || req.getFullName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("fullName es obligatorio");
        }

        String username = req.getUsername().trim();

        if (ownerUserDao.existsUsername(username)) {
            return ResponseEntity.badRequest().body("El username ya existe");
        }

        String hash = passwordEncoder.encode(req.getPassword());

        req.setUsername(username);

        int newId = ownerUserDao.insertUser(req, hash);

        return ResponseEntity.ok("Usuario creado con id=" + newId);
    }
}
