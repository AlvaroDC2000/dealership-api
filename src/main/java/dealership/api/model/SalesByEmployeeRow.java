package dealership.api.model;

import java.math.BigDecimal;

/**
 * Data transfer object representing aggregated sales information per employee.
 * <p>
 * This class contains summary data related to sales performance for a specific
 * employee, including the total number of sales and the total revenue generated.
 * It is typically used in reporting and owner-level dashboard views.
 * </p>
 */
public class SalesByEmployeeRow {

    private int sellerUserId;
    private String employeeName;
    private int salesCount;
    private BigDecimal salesTotal;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and framework deserialization.
     * </p>
     */
    public SalesByEmployeeRow() {}

    /**
     * Creates a new instance with aggregated sales data for an employee.
     *
     * @param sellerUserId the identifier of the selling user
     * @param employeeName the full name of the employee
     * @param salesCount the total number of sales
     * @param salesTotal the total sales revenue amount
     */
    public SalesByEmployeeRow(int sellerUserId, String employeeName, int salesCount, BigDecimal salesTotal) {
        this.sellerUserId = sellerUserId;
        this.employeeName = employeeName;
        this.salesCount = salesCount;
        this.salesTotal = salesTotal;
    }

    /**
     * Returns the identifier of the selling user.
     *
     * @return the seller user identifier
     */
    public int getSellerUserId() {
        return sellerUserId;
    }

    /**
     * Sets the identifier of the selling user.
     *
     * @param sellerUserId the seller user identifier to set
     */
    public void setSellerUserId(int sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    /**
     * Returns the full name of the employee.
     *
     * @return the employee name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Sets the full name of the employee.
     *
     * @param employeeName the employee name to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Returns the total number of sales.
     *
     * @return the sales count
     */
    public int getSalesCount() {
        return salesCount;
    }

    /**
     * Sets the total number of sales.
     *
     * @param salesCount the sales count to set
     */
    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    /**
     * Returns the total sales revenue amount.
     *
     * @return the total sales revenue
     */
    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    /**
     * Sets the total sales revenue amount.
     *
     * @param salesTotal the total sales revenue to set
     */
    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }
}

