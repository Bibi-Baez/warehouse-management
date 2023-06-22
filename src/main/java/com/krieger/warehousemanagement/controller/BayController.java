package com.krieger.warehousemanagement.controller;

import com.krieger.warehousemanagement.dto.BayDTO;
import com.krieger.warehousemanagement.service.BayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse/")
public class BayController {
    @Autowired
    BayService bayService;

    @PostMapping("/{code}/bay")
    public ResponseEntity<BayDTO> createBay(@PathVariable("code") String code,
                                            @Valid @RequestBody BayDTO bayDTO) {
        return new ResponseEntity<>(bayService.createBay(code, bayDTO), HttpStatus.CREATED);
    }

    @GetMapping("{code}/bays")
    public List<BayDTO> getBaysByWarehouseCode(@PathVariable("code") String code) {
        return bayService.getAllBays(code);
    }

    @GetMapping("/{code}/bay/{codeId}")
    public ResponseEntity<BayDTO> getBayByCode(@PathVariable("code") String warehouseCode,
                                               @PathVariable("codeId") String code) {
        BayDTO bayDTO = bayService.getBayByCode(warehouseCode, code);
        return new ResponseEntity<>(bayDTO, HttpStatus.OK);
    }

    @GetMapping("/{code}/bay/tag/{tag}")
    public ResponseEntity<BayDTO> getBayByTag(@PathVariable("code") String warehouseCode,
                                               @PathVariable("tag") String tag) {
        BayDTO bayDTO = bayService.getBayByTag(warehouseCode, tag);
        return new ResponseEntity<>(bayDTO, HttpStatus.OK);
    }

    @PutMapping("/{code}/bay/{codeId}")
    public ResponseEntity<BayDTO> updateBay(@PathVariable("code") String warehouseCode,
                                            @PathVariable("codeId") String bayCode,
                                            @Valid @RequestBody BayDTO bayDTO) {
        BayDTO bayResponse = bayService.updateByCode(warehouseCode, bayCode, bayDTO);
        return new ResponseEntity<>(bayResponse, HttpStatus.OK);

    }

    @DeleteMapping("/{code}/bay/{codeId}")
    public ResponseEntity<String> deleteBay(@PathVariable("code") String warehouseCode,
                                            @PathVariable("codeId") String bayCode) {
        bayService.removeBay(warehouseCode, bayCode);

        return new ResponseEntity<>("Bay successfully deleted", HttpStatus.OK);
    }
}
