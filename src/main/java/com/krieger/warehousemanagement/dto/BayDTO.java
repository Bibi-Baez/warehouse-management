package com.krieger.warehousemanagement.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class BayDTO {

    private Long id;
    @Digits(integer = 1, fraction = 0)
    private int row;

    @Max(3)
    @Min(1)
    private int shelf;

    @Max(10)
    @Min(1)
    private Integer level;

    private String code;

    @NotEmpty(message = "Should contain a type (pallet / cart)")
    private String type;

    @Max(9)
    @Min(1)
    private int holdingPoints;
    private int taken;
    @NotEmpty(message = "Should contain a tag")
    private String tag;

    private String warehouseCode;

    public BayDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHoldingPoints() {
        return holdingPoints;
    }

    public void setHoldingPoints(int holdingPoints) {
        this.holdingPoints = holdingPoints;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
}
