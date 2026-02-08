package dealership.api.dao;

import dealership.api.model.OwnerSummaryRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Data access object responsible for retrieving aggregated owner-level metrics.
 * <p>
 * This DAO provides summary information related to sales, repairs, and vehicle
 * stock, intended to support high-level dashboards and reporting views for
 * the dealership owner.
 * </p>
 */
@Repository
public class OwnerDao {

    private final JdbcTemplate jdbc;

    /**
     * Creates a new DAO instance using the provided {@link JdbcTemplate}.
     * <p>
     * The JDBC template is used to execute SQL queries that retrieve aggregated
     * data from the database.
     * </p>
     *
     * @param jdbc JDBC template used for database access
     */
    public OwnerDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Retrieves a global summary of dealership activity.
     * <p>
     * The returned summary includes aggregated information such as the total
     * number of sales, total sales amount, completed repair count, total repair
     * revenue, and the number of vehicles currently not sold.
     * </p>
     *
     * @return an object containing aggregated owner summary data
     */
    public OwnerSummaryRow getSummary() {

        Integer salesCount = jdbc.queryForObject("SELECT COUNT(*) FROM sale", Integer.class);
        BigDecimal salesTotal = jdbc.queryForObject("SELECT COALESCE(SUM(price), 0) FROM sale", BigDecimal.class);

        Integer repairsCount = jdbc.queryForObject(
                "SELECT COUNT(*) FROM repair_order WHERE status='FINISHED' AND estimated_budget IS NOT NULL",
                Integer.class
        );
        BigDecimal repairsTotal = jdbc.queryForObject(
                "SELECT COALESCE(SUM(estimated_budget), 0) FROM repair_order WHERE status='FINISHED' AND estimated_budget IS NOT NULL",
                BigDecimal.class
        );

        Integer unsoldStockCount = jdbc.queryForObject(
                "SELECT COUNT(*) FROM vehicle WHERE status <> 'SOLD'",
                Integer.class
        );

        return new OwnerSummaryRow(
                salesCount != null ? salesCount : 0,
                salesTotal != null ? salesTotal : BigDecimal.ZERO,
                repairsCount != null ? repairsCount : 0,
                repairsTotal != null ? repairsTotal : BigDecimal.ZERO,
                unsoldStockCount != null ? unsoldStockCount : 0
        );
    }
}
