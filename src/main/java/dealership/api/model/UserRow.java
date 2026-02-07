package dealership.api.model;

public class UserRow {

    private int id;
    private int dealershipId;
    private String dealershipName;

    private int roleId;
    private String roleName;

    private String username;
    private String fullName;
    private boolean active;

    public UserRow() {}

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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDealershipId() { return dealershipId; }
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    public String getDealershipName() { return dealershipName; }
    public void setDealershipName(String dealershipName) { this.dealershipName = dealershipName; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
