package dealership.api.model;

/**
 * Request model used to create a new user.
 * <p>
 * This class represents the data required to register a new employee user
 * within the system. It is typically populated from an incoming HTTP request
 * body and passed through the application layers for validation and persistence.
 * </p>
 */
public class CreateUserRequest {

    private int dealershipId;
    private int roleId;
    private String username;
    private String password;
    private String fullName;
    private boolean active = true;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and request body deserialization.
     * </p>
     */
    public CreateUserRequest() {}

    /**
     * Returns the dealership identifier associated with the user.
     *
     * @return the dealership identifier
     */
    public int getDealershipId() { return dealershipId; }

    /**
     * Sets the dealership identifier for the user.
     *
     * @param dealershipId the dealership identifier to set
     */
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    /**
     * Returns the role identifier assigned to the user.
     *
     * @return the role identifier
     */
    public int getRoleId() { return roleId; }

    /**
     * Sets the role identifier for the user.
     *
     * @param roleId the role identifier to set
     */
    public void setRoleId(int roleId) { this.roleId = roleId; }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username for the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Returns the raw password provided for the user.
     *
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets the raw password for the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Returns the full name of the user.
     *
     * @return the full name
     */
    public String getFullName() { return fullName; }

    /**
     * Sets the full name of the user.
     *
     * @param fullName the full name to set
     */
    public void setFullName(String fullName) { this.fullName = fullName; }

    /**
     * Indicates whether the user is active.
     *
     * @return {@code true} if the user is active, {@code false} otherwise
     */
    public boolean isActive() { return active; }

    /**
     * Sets the active status of the user.
     *
     * @param active the active status to set
     */
    public void setActive(boolean active) { this.active = active; }
}
