/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.entity;

import java.math.BigDecimal;

/**
 *
 * @author Eddy
 */
public class QueryVehicle {
    
    String query, type;
    BigDecimal minPrice, maxPrice, minYear, maxYear;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinYear() {
        return minYear;
    }

    public void setMinYear(BigDecimal minYear) {
        this.minYear = minYear;
    }

    public BigDecimal getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(BigDecimal maxYear) {
        this.maxYear = maxYear;
    }
    
    
    
}
