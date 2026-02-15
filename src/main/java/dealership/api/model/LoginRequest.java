package dealership.api.model;

/**
 * Request body for authentication login.
 * <p>
 * This model represents the payload sent by the client when attempting
 * to authenticate. It contains the credentials required to validate
 * a user against the stored authentication data.
 * </p>
 */
public class LoginRequest {

    private String username;
    private String password;

    /**
     * Default constructor required for JSON deserialization.
     */
    public LoginRequest() {}

    /**
     * Returns the username provided by the client.
     *
     * @return the username used for authentication
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username received in the login request.
     *
     * @param username the username entered by the client
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the plain-text password provided by the client.
     *
     * @return the password entered during login
     */
    public String getPassword() {
        return password;
    }

    /**
     * Plain password sent by the client (Angular).
     * It is validated against the stored BCrypt hash.
     *
     * @param password the raw password entered by the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
