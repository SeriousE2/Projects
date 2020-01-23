/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface ProfessionalDao {
    
    public List<Professional> findAllProfessional() throws DataException;
    public Professional findByProfessionalId(int professionalId) throws DataException;
    public List<Professional> findByProfessionalFirstName(String professionalFirstName) throws DataException;
    public List<Professional> findByProfessionalLastName(String professionalLastName) throws DataException;
    public List<Professional> findByProfessionalSpecialty(SpecialtyType type) throws DataException;
    public List<Professional> findByProfessionalHourlyRate(BigDecimal hourlyRate) throws DataException;
    public Professional findByProfessionalLastNameNumberTwo(String professionalLastName) throws DataException;
}
