package dealership.api.model;

import java.math.BigDecimal;

/**
 * Data transfer object representing aggregated repair revenue for a dealership.
 * <p>
 * This class is used to transport summarized information about completed
 * repairs, including the total revenue generated and the number of repairs
 * performed for a specific dealership.
 * </p>
 */
public class RepairRevenueRow {

    private int dealershipId;
    private BigDecimal importeTotal;
    private int numReparaciones;

    /**
     * Default constructor.
     * <p>
     * Required for object instantiation and framework deserialization.
     * </p>
     */
    public RepairRevenueRow() {}

    /**
     * Creates a new instance with aggregated repair revenue data.
     *
     * @param dealershipId the dealership identifier
     * @param importeTotal the total repair revenue amount
     * @param numReparaciones the number of completed repairs
     */
    public RepairRevenueRow(int dealershipId, BigDecimal importeTotal, int numReparaciones) {
        this.dealershipId = dealershipId;
        this.importeTotal = importeTotal;
        this.numReparaciones = numReparaciones;
    }

    /**
     * Returns the dealership identifier.
     *
     * @return the dealership identifier
     */
    public int getDealershipId() { return dealershipId; }

    /**
     * Sets the dealership identifier.
     *
     * @param dealershipId the dealership identifier to set
     */
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    /**
     * Returns the total repair revenue amount.
     *
     * @return the total repair revenue
     */
    public BigDecimal getImporteTotal() { return importeTotal; }

    /**
     * Sets the total repair revenue amount.
     *
     * @param importeTotal the total repair revenue to set
     */
    public void setImporteTotal(BigDecimal importeTotal) { this.importeTotal = importeTotal; }

    /**
     * Returns the number of completed repairs.
     *
     * @return the number of repairs
     */
    public int getNumReparaciones() { return numReparaciones; }

    /**
     * Sets the number of completed repairs.
     *
     * @param numReparaciones the number of repairs to set
     */
    public void setNumReparaciones(int numReparaciones) { this.numReparaciones = numReparaciones; }
}

