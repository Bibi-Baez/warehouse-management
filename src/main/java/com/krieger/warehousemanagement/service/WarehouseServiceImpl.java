package com.krieger.warehousemanagement.service;

import com.krieger.warehousemanagement.dto.WarehouseDTO;
import com.krieger.warehousemanagement.dto.WarehouseResponse;
import com.krieger.warehousemanagement.entity.Warehouse;
import com.krieger.warehousemanagement.exception.ResourceNotFoundException;
import com.krieger.warehousemanagement.repository.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository whRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = mapToEntity(warehouseDTO);
        Warehouse newWarehouse = whRepo.save(warehouse);
        return mapToDTO(newWarehouse);
    }

    @Override
    public WarehouseResponse getWarehouses(int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Warehouse> warehouses = whRepo.findAll(pageable);
        List<Warehouse> warehousesList = warehouses.getContent();
        List<WarehouseDTO> content = warehousesList.stream().map(warehouse -> mapToDTO(warehouse)).collect(Collectors.toList());
        WarehouseResponse response =new WarehouseResponse();
        response.setWarehouses(content);
        response.setPageNumber(warehouses.getNumber());
        response.setPageSize(warehouses.getSize());
        response.setElementsTotal(warehouses.getTotalElements());
        response.setPagesTotal(warehouses.getTotalPages());
        response.setLast(warehouses.isLast());

        return response;
    }

    @Override
    public WarehouseDTO getWarehouseByCode(String code) {
        Warehouse warehouse = whRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", code));

        return mapToDTO(warehouse);
    }

    @Override
    public WarehouseDTO getWarehouseByName(String name) {
        Warehouse warehouse = whRepo.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse name", "name", name));
        return mapToDTO(warehouse);
    }

    @Override
    public WarehouseDTO updateWarehouseById(WarehouseDTO warehouseDTO, long id) {
        Warehouse warehouse = whRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse id", "id", id));
        warehouse.setAddress(warehouseDTO.getAddress());
        warehouse.setName(warehouseDTO.getName());
        warehouse.setCode(warehouseDTO.getCode());
        Warehouse warehouseUpdated = whRepo.save(warehouse);

        return mapToDTO(warehouseUpdated);
    }

    @Override
    public void deleteWarehouse(String code) {
        Warehouse warehouse = whRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", code));

        whRepo.delete(warehouse);
    }


    public Warehouse mapToEntity(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        return warehouse;
    }

    public WarehouseDTO mapToDTO(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = modelMapper.map(warehouse, WarehouseDTO.class);
        return warehouseDTO;
    }
}
