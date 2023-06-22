package com.krieger.warehousemanagement.repository;

import com.krieger.warehousemanagement.entity.Bay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BayRepository extends JpaRepository<Bay, Long> {

    public List<Bay> findByWarehouseCode(String warehouseCode);
    public Optional<Bay> findByCode(String code);

    public Optional<Bay> findByTag(String tag);

}
