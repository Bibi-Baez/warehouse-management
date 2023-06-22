package com.krieger.warehousemanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bay")
public class Bay {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int _row;
    @Column(nullable = false)
    private int _shelf;
    @Column(nullable = false)
    private int _level;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int _holdingPoints;
    private int _taken;
    @Column(nullable = false, unique = true)
    private String tag;
    @Column(nullable = false)
    private String warehouse_code;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouses_id", nullable = false)
    private Warehouse warehouse;

    public Bay() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int get_row() {
        return _row;
    }

    public void set_row(int _row) {
        this._row = _row;
    }

    public int get_shelf() {
        return _shelf;
    }

    public void set_shelf(int _shelf) {
        this._shelf = _shelf;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
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

    public int get_holdingPoints() {
        return _holdingPoints;
    }

    public void set_holdingPoints(int _holdingPoints) {
        this._holdingPoints = _holdingPoints;
    }

    public int get_taken() {
        return _taken;
    }

    public void set_taken(int _taken) {
        this._taken = _taken;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }
}
