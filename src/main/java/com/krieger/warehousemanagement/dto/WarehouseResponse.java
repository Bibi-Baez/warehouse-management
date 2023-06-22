package com.krieger.warehousemanagement.dto;

import java.util.List;

public class WarehouseResponse {

    private List<WarehouseDTO> warehouses;
    private int pageNumber;
    private int pageSize;
    private long elementsTotal;
    private int pagesTotal;
    private boolean last;

    public WarehouseResponse() {
        super();
    }

    public List<WarehouseDTO> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseDTO> warehouses) {
        this.warehouses = warehouses;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getElementsTotal() {
        return elementsTotal;
    }

    public void setElementsTotal(long elementsTotal) {
        this.elementsTotal = elementsTotal;
    }

    public int getPagesTotal() {
        return pagesTotal;
    }

    public void setPagesTotal(int pagesTotal) {
        this.pagesTotal = pagesTotal;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
