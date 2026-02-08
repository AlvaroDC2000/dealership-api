package dealership.api.dao;

import dealership.api.model.SalesByEmployeeRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access object responsible for sales aggregation queries.
 * <p>
 * This DAO provides read access to aggregated sales data grouped by employee.
 * The retrieved information is typically used for reporting and performance
 * analysis at management or owner level.
 * </p>
 */
@Repository
public class SalesDao {

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
    public SalesDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Retrieves aggregated sales data grouped by employee.
     * <p>
     * The returned data includes the number of sales and total sales amount
     * for each employee, ordered by total amount in descending order.
     * </p>
     *
     * @return a list of sales aggregation rows grouped by employee
     */
    public List<SalesByEmployeeRow> findSalesByEmployee() {
        String sql =
                "SELECT s.seller_user_id AS sellerUserId, " +
                "       u.full_name AS empleado, " +
                "       COUNT(*) AS numVentas, " +
                "       SUM(s.price) AS importeTotal " +
                "FROM sale s " +
                "JOIN `user` u ON u.id = s.seller_user_id " +
                "GROUP BY s.seller_user_id, u.full_name " +
                "ORDER BY importeTotal DESC";

        return jdbc.query(sql, (rs, rowNum) -> new SalesByEmployeeRow(
                rs.getInt("sellerUserId"),
                rs.getString("empleado"),
                rs.getInt("numVentas"),
                rs.getBigDecimal("importeTotal")
        ));
    }
}
