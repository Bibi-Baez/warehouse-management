package com.krieger.warehousemanagement.dto;

import com.krieger.warehousemanagement.entity.Bay;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class WarehouseDTO {
    private Long id;
    @NotEmpty(message = "Should contain 3 digits")
    @Digits(integer = 3, fraction = 0)
    @Size(min=3, max = 3)
    private String code;

    private String name;
    @Size(min = 10, message = "Should contain an Address")
    private String address;
    private Set<Bay> bays;


    public WarehouseDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Bay> getBays() {
        return bays;
    }

    public void setBays(Set<Bay> bays) {
        this.bays = bays;
    }
}
