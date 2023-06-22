package com.krieger.warehousemanagement.service;

import com.krieger.warehousemanagement.entity.Warehouse;
import com.krieger.warehousemanagement.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository whRepo;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        warehouse = new Warehouse();
        warehouse.setCode("123");
        warehouse.setName("Alpha");
        warehouse.setId(1L);
        warehouse.setAddress("Stairway to heaven 25");

    }

    @Test
    void createWarehouse() {
        when(whRepo.save(any(Warehouse.class))).thenReturn(warehouse);
        assertNotNull(whRepo.save(new Warehouse()));
    }

    @Test
    void getWarehouses() {
        when(whRepo.findAll()).thenReturn(Arrays.asList(warehouse));
        assertNotNull(whRepo.findAll());
    }

}