package dealership.api.model;

import java.math.BigDecimal;

public class RepairRevenueRow {

    private int dealershipId;
    private BigDecimal importeTotal;
    private int numReparaciones;

    public RepairRevenueRow() {}

    public RepairRevenueRow(int dealershipId, BigDecimal importeTotal, int numReparaciones) {
        this.dealershipId = dealershipId;
        this.importeTotal = importeTotal;
        this.numReparaciones = numReparaciones;
    }

    public int getDealershipId() { return dealershipId; }
    public void setDealershipId(int dealershipId) { this.dealershipId = dealershipId; }

    public BigDecimal getImporteTotal() { return importeTotal; }
    public void setImporteTotal(BigDecimal importeTotal) { this.importeTotal = importeTotal; }

    public int getNumReparaciones() { return numReparaciones; }
    public void setNumReparaciones(int numReparaciones) { this.numReparaciones = numReparaciones; }
}
