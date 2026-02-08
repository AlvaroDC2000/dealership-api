package dealership.api.dao;

import dealership.api.model.RepairRevenueRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object responsible for repair revenue aggregation queries.
 * <p>
 * This DAO provides read access to aggregated financial data related to
 * completed repair orders, grouped by dealership.
 * </p>
 */
@Repository
public class RepairDao {

    private final JdbcTemplate jdbc;

    /**
     * Creates a new DAO instance using the provided {@link JdbcTemplate}.
     * <p>
     * The JDBC template is used to execute SQL queries against the database.
     * </p>
     *
     * @param jdbc JDBC template used for database access
     */
    public RepairDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Recaudación total por concesionario (solo reparaciones finalizadas con presupuesto).
     * <p>
     * This method retrieves aggregated repair revenue data for each dealership.
     * Only repair orders that are marked as finished and have an associated
     * estimated budget are included in the calculation.
     * </p>
     *
     * @return a list of repair revenue rows grouped by dealership
     */
    public List<RepairRevenueRow> findRepairRevenueByDealership() {

        String sql =
                "SELECT v.current_dealership_id AS dealershipId, " +
                "       COUNT(*) AS numReparaciones, " +
                "       SUM(r.estimated_budget) AS importeTotal " +
                "FROM repair_order r " +
                "JOIN vehicle v ON v.id = r.vehicle_id " +
                "WHERE r.status = 'FINISHED' " +
                "  AND r.estimated_budget IS NOT NULL " +
                "GROUP BY v.current_dealership_id " +
                "ORDER BY importeTotal DESC";

        return jdbc.query(sql, (rs, rowNum) -> new RepairRevenueRow(
                rs.getInt("dealershipId"),
                rs.getBigDecimal("importeTotal"),
                rs.getInt("numReparaciones")
        ));
    }
}
