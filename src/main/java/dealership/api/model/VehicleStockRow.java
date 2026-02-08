package dealership.api.model;

/**
 * Data transfer object representing vehicle stock information.
 * <p>
 * This class is used to expose details about vehicles currently stored
 * in the system, including identification data, technical attributes,
 * and current status. It is typically used to represent unsold vehicle
 * inventory in reporting and management views.
 * </p>
 */
public class VehicleStockRow {

    private int id;
    private String plate;
    private String brand;
    private String model;
    private int year;
    private int mileage;
    private String status;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and framework deserialization.
     * </p>
     */
    public VehicleStockRow() {}

    /**
     * Creates a new instance with all vehicle stock fields populated.
     *
     * @param id the vehicle identifier
     * @param plate the vehicle license plate
     * @param brand the vehicle brand
     * @param model the vehicle model
     * @param year the manufacturing year
     * @param mileage the vehicle mileage
     * @param status the current vehicle status
     */
    public VehicleStockRow(int id, String plate, String brand, String model, int year, int mileage, String status) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.status = status;
    }

    /**
     * Returns the vehicle identifier.
     *
     * @return the vehicle identifier
     */
    public int getId() { return id; }

    /**
     * Sets the vehicle identifier.
     *
     * @param id the vehicle identifier to set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Returns the vehicle license plate.
     *
     * @return the license plate
     */
    public String getPlate() { return plate; }

    /**
     * Sets the vehicle license plate.
     *
     * @param plate the license plate to set
     */
    public void setPlate(String plate) { this.plate = plate; }

    /**
     * Returns the vehicle brand.
     *
     * @return the vehicle brand
     */
    public String getBrand() { return brand; }

    /**
     * Sets the vehicle brand.
     *
     * @param brand the vehicle brand to set
     */
    public void setBrand(String brand) { this.brand = brand; }

    /**
     * Returns the vehicle model.
     *
     * @return the vehicle model
     */
    public String getModel() { return model; }

    /**
     * Sets the vehicle model.
     *
     * @param model the vehicle model to set
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Returns the manufacturing year of the vehicle.
     *
     * @return the vehicle year
     */
    public int getYear() { return year; }

    /**
     * Sets the manufacturing year of the vehicle.
     *
     * @param year the vehicle year to set
     */
    public void setYear(int year) { this.year = year; }

    /**
     * Returns the mileage of the vehicle.
     *
     * @return the vehicle mileage
     */
    public int getMileage() { return mileage; }

    /**
     * Sets the mileage of the vehicle.
     *
     * @param mileage the vehicle mileage to set
     */
    public void setMileage(int mileage) { this.mileage = mileage; }

    /**
     * Returns the current status of the vehicle.
     *
     * @return the vehicle status
     */
    public String getStatus() { return status; }

    /**
     * Sets the current status of the vehicle.
     *
     * @param status the vehicle status to set
     */
    public void setStatus(String status) { this.status = status; }
}
