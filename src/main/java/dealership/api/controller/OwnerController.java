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

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final VehicleDao vehicleDao;
    private final SalesDao salesDao;
    private final RepairDao repairDao;
    private final OwnerDao ownerDao;

    public OwnerController(VehicleDao vehicleDao, SalesDao salesDao, RepairDao repairDao, OwnerDao ownerDao) {
        this.vehicleDao = vehicleDao;
        this.salesDao = salesDao;
        this.repairDao = repairDao;
        this.ownerDao = ownerDao;
    }


    @GetMapping("/stock-unsold")
    public List<VehicleStockRow> getUnsoldStock() {
        return vehicleDao.findUnsoldStock();
    }

    @GetMapping("/sales/by-employee")
    public List<SalesByEmployeeRow> getSalesByEmployee() {
        return salesDao.findSalesByEmployee();
    }

    @GetMapping("/repairs/revenue-by-dealership")
    public List<RepairRevenueRow> getRepairRevenueByDealership() {
        return repairDao.findRepairRevenueByDealership();
    }
    
    @GetMapping("/summary")
    public OwnerSummaryRow getSummary() {
        return ownerDao.getSummary();
    }

}
