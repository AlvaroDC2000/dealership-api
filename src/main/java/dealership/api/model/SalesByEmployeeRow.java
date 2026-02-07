package dealership.api.model;

import java.math.BigDecimal;

public class SalesByEmployeeRow {

    private int sellerUserId;
    private String empleado;
    private int numVentas;
    private BigDecimal importeTotal;

    public SalesByEmployeeRow() {}

    public SalesByEmployeeRow(int sellerUserId, String empleado, int numVentas, BigDecimal importeTotal) {
        this.sellerUserId = sellerUserId;
        this.empleado = empleado;
        this.numVentas = numVentas;
        this.importeTotal = importeTotal;
    }

    public int getSellerUserId() { return sellerUserId; }
    public void setSellerUserId(int sellerUserId) { this.sellerUserId = sellerUserId; }

    public String getEmpleado() { return empleado; }
    public void setEmpleado(String empleado) { this.empleado = empleado; }

    public int getNumVentas() { return numVentas; }
    public void setNumVentas(int numVentas) { this.numVentas = numVentas; }

    public BigDecimal getImporteTotal() { return importeTotal; }
    public void setImporteTotal(BigDecimal importeTotal) { this.importeTotal = importeTotal; }
}

