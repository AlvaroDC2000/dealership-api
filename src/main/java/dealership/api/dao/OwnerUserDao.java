package dealership.api.dao;

import dealership.api.model.CreateUserRequest;
import dealership.api.model.UserRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class OwnerUserDao {

    private final JdbcTemplate jdbc;

    public OwnerUserDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean existsUsername(String username) {
        String sql = "SELECT COUNT(*) FROM `user` u WHERE u.username = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    public List<UserRow> findUsers(Integer dealershipId, Integer roleId, Boolean active) {

        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sql.append("SELECT u.id, u.dealership_id, d.name AS dealership_name, ");
        sql.append("       u.role_id, r.name AS role_name, ");
        sql.append("       u.username, u.full_name, u.is_active ");
        sql.append("FROM `user` u ");
        sql.append("JOIN dealership d ON d.id = u.dealership_id ");
        sql.append("JOIN role r ON r.id = u.role_id ");
        sql.append("WHERE 1=1 ");

        if (dealershipId != null) {
            sql.append("AND u.dealership_id = ? ");
            params.add(dealershipId);
        }
        if (roleId != null) {
            sql.append("AND u.role_id = ? ");
            params.add(roleId);
        }
        if (active != null) {
            sql.append("AND u.is_active = ? ");
            params.add(active ? 1 : 0);
        }

        sql.append("ORDER BY u.id");

        return jdbc.query(
                sql.toString(),
                ps -> {
                    for (int i = 0; i < params.size(); i++) {
                        ps.setObject(i + 1, params.get(i));
                    }
                },
                (rs, rowNum) -> new UserRow(
                        rs.getInt("id"),
                        rs.getInt("dealership_id"),
                        rs.getString("dealership_name"),
                        rs.getInt("role_id"),
                        rs.getString("role_name"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getInt("is_active") == 1
                )
        );
    }

    public int insertUser(CreateUserRequest req, String passwordHash) {

        String sql =
                "INSERT INTO `user` (dealership_id, role_id, username, password_hash, full_name, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, req.getDealershipId());
            ps.setInt(2, req.getRoleId());
            ps.setString(3, req.getUsername());
            ps.setString(4, passwordHash);
            ps.setString(5, req.getFullName());
            ps.setInt(6, req.isActive() ? 1 : 0);
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
