package com.krieger.warehousemanagement.service;

import com.krieger.warehousemanagement.dto.WarehouseDTO;
import com.krieger.warehousemanagement.dto.WarehouseResponse;

public interface WarehouseService {
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO);
    public WarehouseResponse getWarehouses(int pageNumber, int pageSize, String sortByCode, String sortDirection);
    public WarehouseDTO getWarehouseByCode(String warehouseCode);
    public WarehouseDTO getWarehouseByName(String warehouseCode);
    public WarehouseDTO updateWarehouseById(WarehouseDTO warehouseDTO, long id);
    public void deleteWarehouse(String code);
}
