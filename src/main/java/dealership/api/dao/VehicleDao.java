package dealership.api.dao;

import dealership.api.model.VehicleStockRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object responsible for vehicle stock queries.
 * <p>
 * This DAO provides read access to vehicle inventory data, focusing on
 * vehicles that are currently not sold and therefore available as stock.
 * </p>
 */
@Repository
public class VehicleDao {

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
    public VehicleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Devuelve vehículos NO vendidos (stock).
     * Nota: Si tu esquema usa otro campo en lugar de status, lo ajustamos en 10s.
     * <p>
     * This method retrieves vehicles that are not marked as sold in the database.
     * The result represents the current unsold inventory available in the
     * dealership.
     * </p>
     *
     * @return a list of unsold vehicle stock rows
     */
    public List<VehicleStockRow> findUnsoldStock() {
        String sql =
                "SELECT v.id, v.plate, v.brand, v.model, v.year, v.mileage, v.status " +
                "FROM vehicle v " +
                "WHERE v.status <> 'SOLD' " +
                "ORDER BY v.id DESC";

        return jdbc.query(sql, (rs, rowNum) -> new VehicleStockRow(
                rs.getInt("id"),
                rs.getString("plate"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getInt("year"),
                rs.getInt("mileage"),
                rs.getString("status")
        ));
    }

}
