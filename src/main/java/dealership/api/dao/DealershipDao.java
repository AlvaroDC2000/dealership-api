package dealership.api.dao;

import dealership.api.model.IdNameRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DealershipDao {

    private final JdbcTemplate jdbc;

    public DealershipDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<IdNameRow> findAllDealerships() {
        String sql = "SELECT d.id, d.name FROM dealership d ORDER BY d.id";
        return jdbc.query(sql, (rs, rowNum) -> new IdNameRow(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }
}
