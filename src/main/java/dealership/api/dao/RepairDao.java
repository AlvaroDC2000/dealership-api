package dealership.api.dao;

import dealership.api.model.RepairRevenueRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepairDao {

    private final JdbcTemplate jdbc;

    public RepairDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Recaudación total por concesionario (solo reparaciones finalizadas con presupuesto).
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
