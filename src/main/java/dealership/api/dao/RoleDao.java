package dealership.api.dao;

import dealership.api.model.IdNameRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDao {

    private final JdbcTemplate jdbc;

    public RoleDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<IdNameRow> findAllRoles() {
        String sql = "SELECT r.id, r.name FROM role r ORDER BY r.id";
        return jdbc.query(sql, (rs, rowNum) -> new IdNameRow(
                rs.getInt("id"),
                rs.getString("name")
        ));
    }
}
