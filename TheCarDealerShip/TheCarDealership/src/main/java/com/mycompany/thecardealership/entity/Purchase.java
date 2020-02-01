/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Eddy
 */
public class Purchase {
    int purchaseId;
    Vehicle vehicle;
    Profile customerProfile;
    BigDecimal salePrice;
    String saleType;
    User createdBy;
    LocalDateTime dateAdded;
    
    public Purchase() {
        this.dateAdded = LocalDateTime.now();
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Profile getCustomerProfile() {
        return customerProfile;
    }

    public void setCustomerProfile(Profile customerProfile) {
        this.customerProfile = customerProfile;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
}
