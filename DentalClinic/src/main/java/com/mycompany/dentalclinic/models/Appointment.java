/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Eddy
 */
public class Appointment {
    
    private int customerId;
    private String dentalProLastname;
    private SpecialtyType type;
    private LocalTime start;
    private LocalTime end;
    private BigDecimal totalCost;
    private String notes;
    private LocalDate date;
    
    public Appointment (){
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
        public Appointment(int customerId, String dentalProLastname, SpecialtyType type, LocalTime start, LocalTime end, BigDecimal totalCost, String notes) {
        this.customerId = customerId;
        this.dentalProLastname = dentalProLastname;
        this.type = type;
        this.start = start;
        this.end = end;
        this.totalCost = totalCost;
        this.notes = notes;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDentalProLastname() {
        return dentalProLastname;
    }

    public void setDentalProLastname(String dentalProLastname) {
        this.dentalProLastname = dentalProLastname;
    }

    public SpecialtyType getType() {
        return type;
    }

    public void setType(SpecialtyType type) {
        this.type = type;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
