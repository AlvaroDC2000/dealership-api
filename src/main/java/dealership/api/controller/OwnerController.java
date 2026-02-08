package dealership.api.controller;

import dealership.api.dao.OwnerDao;
import dealership.api.dao.RepairDao;
import dealership.api.dao.SalesDao;
import dealership.api.dao.VehicleDao;
import dealership.api.model.OwnerSummaryRow;
import dealership.api.model.RepairRevenueRow;
import dealership.api.model.SalesByEmployeeRow;
import dealership.api.model.VehicleStockRow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing endpoints intended for the owner role.
 * <p>
 * This controller aggregates high-level business data related to vehicle stock,
 * sales performance, repair revenue, and overall dealership metrics.
 * The exposed endpoints are designed to support dashboard and reporting views
 * for the dealership owner.
 * </p>
 */
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final VehicleDao vehicleDao;
    private final SalesDao salesDao;
    private final RepairDao repairDao;
    private final OwnerDao ownerDao;

    /**
     * Creates a new controller instance with all required data access objects.
     * <p>
     * Dependencies are provided through constructor injection to allow the
     * controller to retrieve aggregated and domain-specific data from the
     * persistence layer.
     * </p>
     *
     * @param vehicleDao data access object used for vehicle-related queries
     * @param salesDao data access object used for sales-related queries
     * @param repairDao data access object used for repair-related queries
     * @param ownerDao data access object used for owner summary queries
     */
    public OwnerController(VehicleDao vehicleDao, SalesDao salesDao, RepairDao repairDao, OwnerDao ownerDao) {
        this.vehicleDao = vehicleDao;
        this.salesDao = salesDao;
        this.repairDao = repairDao;
        this.ownerDao = ownerDao;
    }


    /**
     * Retrieves the list of vehicles that have not been sold.
     * <p>
     * This endpoint returns stock information for vehicles that are currently
     * available in the dealership inventory and have not yet been associated
     * with a completed sale.
     * </p>
     *
     * @return a list of unsold vehicle stock rows
     */
    @GetMapping("/stock-unsold")
    public List<VehicleStockRow> getUnsoldStock() {
        return vehicleDao.findUnsoldStock();
    }

    /**
     * Retrieves aggregated sales data grouped by employee.
     * <p>
     * The returned data provides insight into sales performance per employee,
     * typically used for reporting and management purposes.
     * </p>
     *
     * @return a list of sales totals grouped by employee
     */
    @GetMapping("/sales/by-employee")
    public List<SalesByEmployeeRow> getSalesByEmployee() {
        return salesDao.findSalesByEmployee();
    }

    /**
     * Retrieves repair revenue aggregated by dealership.
     * <p>
     * This endpoint returns financial data related to completed repairs,
     * grouped by dealership, and is intended for revenue analysis.
     * </p>
     *
     * @return a list of repair revenue rows grouped by dealership
     */
    @GetMapping("/repairs/revenue-by-dealership")
    public List<RepairRevenueRow> getRepairRevenueByDealership() {
        return repairDao.findRepairRevenueByDealership();
    }
    
    /**
     * Retrieves a global summary for the owner dashboard.
     * <p>
     * The summary typically includes high-level metrics such as total sales,
     * total repair revenue, and other aggregated indicators relevant to the
     * dealership owner.
     * </p>
     *
     * @return an object containing aggregated owner summary data
     */
    @GetMapping("/summary")
    public OwnerSummaryRow getSummary() {
        return ownerDao.getSummary();
    }

}

