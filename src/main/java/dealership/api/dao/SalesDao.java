package dealership.api.dao;

import dealership.api.model.SalesByEmployeeRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalesDao {

    private final JdbcTemplate jdbc;

    public SalesDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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

