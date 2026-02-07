package dealership.api.dao;

import dealership.api.model.OwnerSummaryRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class OwnerDao {

    private final JdbcTemplate jdbc;

    public OwnerDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public OwnerSummaryRow getSummary() {

        Integer numVentas = jdbc.queryForObject("SELECT COUNT(*) FROM sale", Integer.class);
        BigDecimal importeVentas = jdbc.queryForObject("SELECT COALESCE(SUM(price), 0) FROM sale", BigDecimal.class);

        Integer numReparaciones = jdbc.queryForObject(
                "SELECT COUNT(*) FROM repair_order WHERE status='FINISHED' AND estimated_budget IS NOT NULL",
                Integer.class
        );
        BigDecimal importeReparaciones = jdbc.queryForObject(
                "SELECT COALESCE(SUM(estimated_budget), 0) FROM repair_order WHERE status='FINISHED' AND estimated_budget IS NOT NULL",
                BigDecimal.class
        );

        Integer numStockNoVendido = jdbc.queryForObject(
                "SELECT COUNT(*) FROM vehicle WHERE status <> 'SOLD'",
                Integer.class
        );

        return new OwnerSummaryRow(
                numVentas != null ? numVentas : 0,
                importeVentas != null ? importeVentas : BigDecimal.ZERO,
                numReparaciones != null ? numReparaciones : 0,
                importeReparaciones != null ? importeReparaciones : BigDecimal.ZERO,
                numStockNoVendido != null ? numStockNoVendido : 0
        );
    }
}
