package dealership.api.dao;

import dealership.api.model.IdNameRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object responsible for dealership-related queries.
 * <p>
 * This DAO provides read access to dealership reference data stored
 * in the database. It is typically used to retrieve basic dealership
 * information for selection lists and filtering purposes.
 * </p>
 */
@Repository
public class DealershipDao {

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
    public DealershipDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Retrieves all dealerships ordered by identifier.
     * <p>
     * Each returned row contains the dealership identifier and name,
     * mapped to a simple data transfer object.
     * </p>
     *
     * @return a list of dealership identifier and name rows
     */
    public List<IdNameRow> findAllDealerships() {
        String sql = "SELECT d.id, d.name FROM dealership d ORDER BY d.id";
        return jdbc.query(sql, (rs, rowNum) -> new IdNameRow(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }
}
