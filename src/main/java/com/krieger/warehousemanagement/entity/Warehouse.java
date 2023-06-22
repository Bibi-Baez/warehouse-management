package com.krieger.warehousemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bay> bays = new HashSet<>();

    public Warehouse() {
        super();
    }

    public Warehouse(Long id, String code, String name, String address) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
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
