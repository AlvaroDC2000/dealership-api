package dealership.api.model;

/**
 * Simple data transfer object representing an identifier and a name.
 * <p>
 * This class is commonly used to return lightweight reference data,
 * such as roles or dealerships, where only an identifier and a
 * human-readable name are required.
 * </p>
 */
public class IdNameRow {

    private int id;
    private String name;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and framework deserialization.
     * </p>
     */
    public IdNameRow() {}

    /**
     * Creates a new instance with the provided identifier and name.
     *
     * @param id the identifier value
     * @param name the associated name
     */
    public IdNameRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the identifier.
     *
     * @return the identifier value
     */
    public int getId() { return id; }

    /**
     * Sets the identifier.
     *
     * @param id the identifier value to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Returns the name.
     *
     * @return the name value
     */
    public String getName() { return name; }

    /**
     * Sets the name.
     *
     * @param name the name value to set
     */
    public void setName(String name) { this.name = name; }
}
