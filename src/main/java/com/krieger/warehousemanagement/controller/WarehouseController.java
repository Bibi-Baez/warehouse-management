package com.krieger.warehousemanagement.controller;

import com.krieger.warehousemanagement.dto.WarehouseDTO;
import com.krieger.warehousemanagement.dto.WarehouseResponse;
import com.krieger.warehousemanagement.service.WarehouseService;
import com.krieger.warehousemanagement.util.WarehouseUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public WarehouseResponse getWarehouses(@RequestParam(value = "pageNumber", defaultValue = WarehouseUtil.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
                                           @RequestParam(value = "pageSize", defaultValue = WarehouseUtil.DEFAULT_SIZE_NUMBER, required = false) int pageSize,
                                           @RequestParam(value = "sortBy", defaultValue = WarehouseUtil.DEFAULT_ORDER_BY, required = false) String sortByCode,
                                           @RequestParam(value = "sortDirection", defaultValue = WarehouseUtil.DEFAULT_ORDER_DIRECTION, required = false) String sortDirection) {
        return warehouseService.getWarehouses(pageNumber, pageSize, sortByCode, sortDirection);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<WarehouseDTO> getWareHouseByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(warehouseService.getWarehouseByCode(code));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<WarehouseDTO> getWareHouseByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(warehouseService.getWarehouseByName(name));
    }

    @PostMapping
    public ResponseEntity<WarehouseDTO> createWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        return new ResponseEntity<>(warehouseService.createWarehouse(warehouseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateWarehouseById(@Valid @RequestBody WarehouseDTO warehouseDTO,
                                                            @PathVariable("id") long id) {

        WarehouseDTO warehouseResponse = warehouseService.updateWarehouseById(warehouseDTO, id);
        return new ResponseEntity<>(warehouseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable("id") String code) {
        warehouseService.deleteWarehouse(code);
        return new ResponseEntity<>("Warehouse successfully deleted", HttpStatus.OK);
    }
}
