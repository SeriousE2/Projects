/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.models;

import com.mycompany.dentalclinic.service.TimeSlot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Eddy
 */
public class Professional {
    
    private int professionalId;
    private String professionalFirstName;
    private String professionalLastName;
    private SpecialtyType type;
    private BigDecimal hourlyRate;
    
    private List<TimeSlot> openTimeSlots = new ArrayList<>();
    private List<TimeSlot> filedTimeSlots = new ArrayList<>();

    public Professional() {    
    }
    
    public Professional(int professionalId, String professionalFirstName, String professionalLastName, SpecialtyType type, BigDecimal hourlyRate) {
        this.professionalId = professionalId;
        this.professionalFirstName = professionalFirstName;
        this.professionalLastName = professionalLastName;
        this.type = type;
        this.hourlyRate = hourlyRate;
    }

    public int getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    public String getProfessionalFirstName() {
        return professionalFirstName;
    }

    public void setProfessionalFirstName(String professionalFirstName) {
        this.professionalFirstName = professionalFirstName;
    }

    public String getProfessionalLastName() {
        return professionalLastName;
    }

    public void setProfessionalLastName(String professionalLastName) {
        this.professionalLastName = professionalLastName;
    }

    public SpecialtyType getType() {
        return type;
    }

    public void setType(SpecialtyType type) {
        this.type = type;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return this.professionalFirstName + " " + this.professionalLastName + " " + this.getType();
    }

    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.professionalId;
        hash = 71 * hash + Objects.hashCode(this.professionalFirstName);
        hash = 71 * hash + Objects.hashCode(this.professionalLastName);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.hourlyRate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professional other = (Professional) obj;
        if (this.professionalId != other.professionalId) {
            return false;
        }
        if (!Objects.equals(this.professionalFirstName, other.professionalFirstName)) {
            return false;
        }
        if (!Objects.equals(this.professionalLastName, other.professionalLastName)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.hourlyRate, other.hourlyRate)) {
            return false;
        }
        return true;
    }

    
    
    
}
