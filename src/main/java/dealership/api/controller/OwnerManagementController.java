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

@RestController
@RequestMapping("/api/owner")
public class OwnerManagementController {

    private final RoleDao roleDao;
    private final DealershipDao dealershipDao;
    private final OwnerUserDao ownerUserDao;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public OwnerManagementController(RoleDao roleDao, DealershipDao dealershipDao, OwnerUserDao ownerUserDao) {
        this.roleDao = roleDao;
        this.dealershipDao = dealershipDao;
        this.ownerUserDao = ownerUserDao;
    }

    @GetMapping("/roles")
    public List<IdNameRow> getRoles() {
        return roleDao.findAllRoles();
    }

    @GetMapping("/dealerships")
    public List<IdNameRow> getDealerships() {
        return dealershipDao.findAllDealerships();
    }

    /**
     * Lista de empleados con filtros opcionales:
     * /api/owner/users?dealershipId=1&roleId=2&active=true
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
     * Crear empleado.
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

        // Guardamos username limpio
        req.setUsername(username);

        int newId = ownerUserDao.insertUser(req, hash);

        return ResponseEntity.ok("Usuario creado con id=" + newId);
    }
}
