package dealership.api.model;

/**
 * Data transfer object representing a user with related dealership and role information.
 * <p>
 * This class is used to expose user data in owner-level management and reporting
 * contexts. It aggregates basic user details together with the associated
 * dealership and role metadata.
 * </p>
 */
public class UserRow {

    private int id;
    private int dealershipId;
    private String dealershipName;

    private int roleId;
    private String roleName;

    private String username;
    private String fullName;
    private boolean active;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and framework deserialization.
     * </p>
     */
    public UserRow() {}

    /**
     * Creates a new instance with all user-related fields populated.
     *
     * @param id the user identifier
     * @param dealershipId the associated dealership identifier
     * @param dealershipName the associated dealership name
     * @param roleId the role identifier assigned to the user
     * @param roleName the role name assigned to the user
     * @param username the username used for authentication
     * @param fullName the full name of the user
     * @param active whether the user is active
     */
    public UserRow(int id, int dealershipId, String dealershipName, int roleId, String roleName,
                   String username, String fullName, boolean active) {
        this.id = id;
        this.dealershipId = dealershipId;
        this.dealershipName = dealershipName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.username = username;
        this.fullName = fullName;
        this.active = active;
    }

    /**
     * Returns the user identifier.
     *
     * @return the user identifier
     */
    public int getId() { return id; }

    /**
     * Sets the user identifier.
     *
     * @param id the user identifier to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Returns the dealership identifier associated with the user.
     *
     * @return the dealership identifier
     */
    public int getDealershipId() { return dealershipId; }

    /**
     * Sets the dealership identifier associated with the user.
     *
     * @param dealershipId the dealership identifier to set
     */
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    /**
     * Returns the name of the associated dealership.
     *
     * @return the dealership name
     */
    public String getDealershipName() { return dealershipName; }

    /**
     * Sets the name of the associated dealership.
     *
     * @param dealershipName the dealership name to set
     */
    public void setDealershipName(String dealershipName) { this.dealershipName = dealershipName; }

    /**
     * Returns the role identifier assigned to the user.
     *
     * @return the role identifier
     */
    public int getRoleId() { return roleId; }

    /**
     * Sets the role identifier assigned to the user.
     *
     * @param roleId the role identifier to set
     */
    public void setRoleId(int roleId) { this.roleId = roleId; }

    /**
     * Returns the role name assigned to the user.
     *
     * @return the role name
     */
    public String getRoleName() { return roleName; }

    /**
     * Sets the role name assigned to the user.
     *
     * @param roleName the role name to set
     */
    public void setRoleName(String roleName) { this.roleName = roleName; }

    /**
     * Returns the username used by the user.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username used by the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) { this.username = username; }

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
