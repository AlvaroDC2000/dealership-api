package dealership.api.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * DAO responsible for authentication lookups.
 * Level 1 scope: username/password validation for UI login.
 * <p>
 * This repository provides database access methods required during the
 * authentication process. It retrieves the minimal set of user data
 * necessary to validate credentials and build a login response.
 * </p>
 */
@Repository
public class AuthDao {

    private final JdbcTemplate jdbc;

    /**
     * Creates a new {@code AuthDao} with the provided {@link JdbcTemplate}.
     *
     * @param jdbc the JDBC template used to execute SQL queries
     */
    public AuthDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Loads the login data for a given username.
     *
     * <p>
     * This method retrieves the minimal user information required for
     * authentication, including role and password hash. Only active users
     * are considered.
     * </p>
     *
     * @param username username to search
     * @return an {@link Optional} containing the login user data if found;
     *         otherwise {@link Optional#empty()}
     */
    public Optional<LoginUserRow> findLoginUserByUsername(String username) {

        String sql =
                "SELECT u.id, u.dealership_id, r.name AS role_name, u.username, u.full_name, u.password_hash " +
                "FROM `user` u " +
                "JOIN role r ON r.id = u.role_id " +
                "WHERE u.username = ? AND u.is_active = 1";

        List<LoginUserRow> rows = jdbc.query(
                sql,
                ps -> ps.setString(1, username),
                (rs, rowNum) -> new LoginUserRow(
                        rs.getInt("id"),
                        rs.getInt("dealership_id"),
                        rs.getString("role_name"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("password_hash")
                )
        );

        return rows.isEmpty() ? Optional.empty() : Optional.of(rows.get(0));
    }

    /**
     * Minimal row used only for login validation.
     * <p>
     * This inner static class represents a lightweight projection of the
     * {@code user} entity containing only the fields required during the
     * authentication process.
     * </p>
     */
    public static class LoginUserRow {

        private final int id;
        private final int dealershipId;
        private final String roleName;
        private final String username;
        private final String fullName;
        private final String passwordHash;

        /**
         * Creates a new immutable login row instance.
         *
         * @param id user identifier
         * @param dealershipId associated dealership identifier
         * @param roleName role name assigned to the user
         * @param username username used for authentication
         * @param fullName full display name of the user
         * @param passwordHash stored BCrypt password hash
         */
        public LoginUserRow(int id, int dealershipId, String roleName, String username, String fullName, String passwordHash) {
            this.id = id;
            this.dealershipId = dealershipId;
            this.roleName = roleName;
            this.username = username;
            this.fullName = fullName;
            this.passwordHash = passwordHash;
        }

        /**
         * Returns the user identifier.
         *
         * @return user ID
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the dealership identifier associated with the user.
         *
         * @return dealership ID
         */
        public int getDealershipId() {
            return dealershipId;
        }

        /**
         * Returns the role name assigned to the user.
         *
         * @return role name
         */
        public String getRoleName() {
            return roleName;
        }

        /**
         * Returns the username used for authentication.
         *
         * @return username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Returns the full display name of the user.
         *
         * @return full name
         */
        public String getFullName() {
            return fullName;
        }

        /**
         * Returns the stored password hash.
         *
         * @return BCrypt password hash
         */
        public String getPasswordHash() {
            return passwordHash;
        }
    }
}
