package dealership.api.model;

import java.math.BigDecimal;

public class OwnerSummaryRow {

    private int numVentas;
    private BigDecimal importeVentas;

    private int numReparaciones;
    private BigDecimal importeReparaciones;

    private int numStockNoVendido;

    public OwnerSummaryRow() {}

    public OwnerSummaryRow(int numVentas, BigDecimal importeVentas,
                           int numReparaciones, BigDecimal importeReparaciones,
                           int numStockNoVendido) {
        this.numVentas = numVentas;
        this.importeVentas = importeVentas;
        this.numReparaciones = numReparaciones;
        this.importeReparaciones = importeReparaciones;
        this.numStockNoVendido = numStockNoVendido;
    }

    public int getNumVentas() { return numVentas; }
    public void setNumVentas(int numVentas) { this.numVentas = numVentas; }

    public BigDecimal getImporteVentas() { return importeVentas; }
    public void setImporteVentas(BigDecimal importeVentas) { this.importeVentas = importeVentas; }

    public int getNumReparaciones() { return numReparaciones; }
    public void setNumReparaciones(int numReparaciones) { this.numReparaciones = numReparaciones; }

    public BigDecimal getImporteReparaciones() { return importeReparaciones; }
    public void setImporteReparaciones(BigDecimal importeReparaciones) { this.importeReparaciones = importeReparaciones; }

    public int getNumStockNoVendido() { return numStockNoVendido; }
    public void setNumStockNoVendido(int numStockNoVendido) { this.numStockNoVendido = numStockNoVendido; }
}
