package dealership.api.model;

/**
 * Response returned after a successful login.
 * Level 1 scope: used only by the UI (no tokens, no backend authorization).
 * <p>
 * This model represents the session information returned to the client
 * once authentication has been successfully validated. It contains only
 * the data required by the UI to manage client-side session state.
 * </p>
 */
public class LoginResponse {

    private int userId;
    private int dealershipId;
    private String role;
    private String username;
    private String fullName;

    /**
     * Default constructor required for JSON serialization/deserialization.
     */
    public LoginResponse() {}

    /**
     * Creates a new login response with the provided session information.
     *
     * @param userId the authenticated user's identifier
     * @param dealershipId the dealership associated with the user
     * @param role the role name assigned to the user
     * @param username the username used for authentication
     * @param fullName the full display name of the user
     */
    public LoginResponse(int userId, int dealershipId, String role, String username, String fullName) {
        this.userId = userId;
        this.dealershipId = dealershipId;
        this.role = role;
        this.username = username;
        this.fullName = fullName;
    }

    /**
     * Returns the authenticated user's identifier.
     *
     * @return user ID
     */
    public int getUserId() {
        return userId;
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
     * Returns the role assigned to the user.
     *
     * @return role name
     */
    public String getRole() {
        return role;
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
     * Sets the user identifier.
     *
     * @param userId user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the dealership identifier.
     *
     * @param dealershipId dealership ID
     */
    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    /**
     * Sets the role assigned to the user.
     *
     * @param role role name
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the username.
     *
     * @param username username used for authentication
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the full display name of the user.
     *
     * @param fullName full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
