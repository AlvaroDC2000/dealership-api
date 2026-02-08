package dealership.api.dao;

import dealership.api.model.IdNameRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object responsible for role-related queries.
 * <p>
 * This DAO provides read access to role reference data stored in the database.
 * It is typically used to retrieve role identifiers and names for management
 * and filtering purposes.
 * </p>
 */
@Repository
public class RoleDao {

    private final JdbcTemplate jdbc;

    /**
     * Creates a new DAO instance using the provided {@link JdbcTemplate}.
     * <p>
     * The JDBC template is used to execute SQL queries against the
     * underlying database.
     * </p>
     *
     * @param jdbc JDBC template used for database access
     */
    public RoleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Retrieves all roles ordered by identifier.
     * <p>
     * Each returned row contains the role identifier and name, mapped
     * to a simple data transfer object.
     * </p>
     *
     * @return a list of role identifier and name rows
     */
    public List<IdNameRow> findAllRoles() {
        String sql = "SELECT r.id, r.name FROM role r ORDER BY r.id";
        return jdbc.query(sql, (rs, rowNum) -> new IdNameRow(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }
}
