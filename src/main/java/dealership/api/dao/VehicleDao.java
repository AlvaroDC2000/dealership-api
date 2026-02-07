package dealership.api.dao;

import dealership.api.model.VehicleStockRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleDao {

    private final JdbcTemplate jdbc;

    public VehicleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Devuelve vehículos NO vendidos (stock).
     * Nota: Si tu esquema usa otro campo en lugar de status, lo ajustamos en 10s.
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
