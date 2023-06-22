package com.krieger.warehousemanagement.service;

import com.krieger.warehousemanagement.dto.BayDTO;

import java.util.List;

public interface BayService {

    public BayDTO createBay(String warehouseCode, BayDTO bayDTO);
    public List<BayDTO> getAllBays(String warehouseCode);
    public BayDTO getBayByCode(String warehouseCode, String code);
    public BayDTO getBayByTag(String warehouseCode, String tag);
    public BayDTO updateByCode(String warehouseCode, String code, BayDTO bayDTO);

    public void removeBay(String warehouseCode, String code);

}
