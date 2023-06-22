package com.krieger.warehousemanagement.controller;

import com.krieger.warehousemanagement.dto.WarehouseDTO;
import com.krieger.warehousemanagement.entity.Warehouse;
import com.krieger.warehousemanagement.service.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WarehouseControllerTest {

    @InjectMocks
    private WarehouseController warehouseController;

    @Mock
    private WarehouseService warehouseService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void getWareHouseByCode() {

        Mockito.when(warehouseService.getWarehouseByCode(Mockito.anyString())).thenReturn(new WarehouseDTO());

        ResponseEntity<WarehouseDTO> response = warehouseController.getWareHouseByCode("Alpha");

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}