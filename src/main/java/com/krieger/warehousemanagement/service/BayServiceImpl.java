package com.krieger.warehousemanagement.service;

import com.krieger.warehousemanagement.dto.BayDTO;
import com.krieger.warehousemanagement.entity.Bay;
import com.krieger.warehousemanagement.entity.Warehouse;
import com.krieger.warehousemanagement.exception.ResourceNotFoundException;
import com.krieger.warehousemanagement.exception.WarehouseManagementException;
import com.krieger.warehousemanagement.repository.BayRepository;
import com.krieger.warehousemanagement.repository.WarehouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BayServiceImpl implements BayService {

    private Logger log = LoggerFactory.getLogger(BayServiceImpl.class);

    @Autowired
    BayRepository bayRepo;
    @Autowired
    WarehouseRepository whRepo;

    @Override
    public BayDTO createBay(String warehouseCode, BayDTO bayDTO) {
        Warehouse warehouse = whRepo.findByCode(warehouseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", warehouseCode));

        if (bayDTO.getHoldingPoints() < bayDTO.getTaken())
            throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Busy holding points are bigger than holding points");

        warehouse.setCode(warehouseCode);
        Bay bay = mapToEntity(bayDTO);
        StringBuilder code = new StringBuilder();
        code.append(bayDTO.getRow());
        code.append(bayDTO.getShelf());
        code.append(bayDTO.getLevel());
        bay.setCode(code.toString());
        bay.setWarehouse(warehouse);
        bay.setWarehouse_code(warehouseCode);
        Bay newBay = bayRepo.save(bay);

        return mapToDTO(newBay);
    }

    @Override
    public List<BayDTO> getAllBays(String warehouseCode) {
        List<Bay> bays = bayRepo.findByWarehouseCode(warehouseCode);
        return bays.stream().map(bay -> mapToDTO(bay)).collect(Collectors.toList());
    }

    @Override
    public BayDTO getBayByCode(String warehouseCode, String code) {
        Warehouse warehouse = whRepo.findByCode(warehouseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", warehouseCode));

        Bay bay = bayRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Bay code", "code", code));


        if (!bay.getWarehouse().getCode().equals(warehouse.getCode())) {
            throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Bay code does not belong to this warehouse");
        }
        return mapToDTO(bay);
    }

    @Override
    public BayDTO getBayByTag(String warehouseCode, String tag) {
        Warehouse warehouse = whRepo.findByCode(warehouseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", warehouseCode));

        Bay bay = bayRepo.findByTag(tag)
                .orElseThrow(() -> new ResourceNotFoundException("Bay tag", "tag", tag));

        if (!bay.getWarehouse().getCode().equals(warehouse.getCode())) {
            throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Bay tag does not belong to this warehouse");
        }
        return mapToDTO(bay);
    }

    @Override
    public BayDTO updateByCode(String warehouseCode, String code, BayDTO bayDTO) {
        Warehouse warehouse = whRepo.findByCode(warehouseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", warehouseCode));

        Bay bay = bayRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Bay code", "code", code));

        if (!bay.getWarehouse().getCode().equals(warehouse.getCode())) {
            throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Bay code does not belong to this warehouse");
        } else if (bayDTO.getHoldingPoints() != bay.get_holdingPoints()) {
            if (bayDTO.getTaken() != bay.get_taken()) {
                if (bayDTO.getHoldingPoints() < bayDTO.getTaken())
                    throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Busy holding points are bigger than holding points");
            } else if (bayDTO.getHoldingPoints() < bay.get_taken())
                throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Busy holding points are bigger than holding points");

        } else if (bayDTO.getHoldingPoints() == bay.get_holdingPoints()) {
            if (bayDTO.getTaken() != bay.get_taken())
                if (bayDTO.getHoldingPoints() < bayDTO.getTaken())
                    throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Busy holding points are bigger than holding points");
        }

        bay.set_taken(bayDTO.getTaken());
        bay.set_holdingPoints(bayDTO.getHoldingPoints());
        bay.setType(bayDTO.getType());
        Bay newBay = bayRepo.save(bay);

        return mapToDTO(newBay);
    }

    @Override
    public void removeBay(String warehouseCode, String code) {
        Warehouse warehouse = whRepo.findByCode(warehouseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse code", "code", warehouseCode));

        Bay bay = bayRepo.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Bay code", "code", code));

        if (!bay.getWarehouse().getCode().equals(warehouse.getCode()))
            throw new WarehouseManagementException(HttpStatus.BAD_REQUEST, "Bay code does not belong to this warehouse");

        bayRepo.delete(bay);

    }

    public Bay mapToEntity(BayDTO bayDTO) {
        Bay bay = new Bay();
        bay.setCode(bayDTO.getCode());
        bay.setId(bayDTO.getId());
        bay.setTag(bayDTO.getTag());
        bay.setType(bayDTO.getType());
        bay.set_row(bayDTO.getRow());
        bay.set_level(bayDTO.getLevel());
        bay.set_shelf(bayDTO.getShelf());
        bay.set_holdingPoints(bayDTO.getHoldingPoints());
        bay.set_taken(bayDTO.getTaken());
        bay.setWarehouse_code(bayDTO.getWarehouseCode());
        return bay;
    }

    public BayDTO mapToDTO(Bay bay) {
        BayDTO bayDTO = new BayDTO();
        bayDTO.setCode(bay.getCode());
        bayDTO.setId(bay.getId());
        bayDTO.setTag(bay.getTag());
        bayDTO.setType(bay.getType());
        bayDTO.setRow(bay.get_row());
        bayDTO.setLevel(bay.get_level());
        bayDTO.setShelf(bay.get_shelf());
        bayDTO.setHoldingPoints(bay.get_holdingPoints());
        bayDTO.setTaken(bay.get_taken());
        bayDTO.setWarehouseCode(bay.getWarehouse_code());
        return bayDTO;
    }
}
