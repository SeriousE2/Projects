/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.service;

import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.data.ProfessionalDao;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddy
 */
public class ProfessionalService {
    
    private ProfessionalDao professionalDao;

    public ProfessionalService(ProfessionalDao professionalDao) {
        this.professionalDao = professionalDao;
    }
    
//    public List<Professional> findByProLastNameWith(String prefix) {
//    
//    }
    
    public List<Professional> findByProLastNameStartingWith(String prefix) {
        try {
            return professionalDao.findByProfessionalLastName(prefix);
        } catch (DataException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Professional> getAllProfessionals() throws DataException{
        return professionalDao.findAllProfessional();
    }
    
    public List<Professional> findByProLastName(String prefix){
        try {
            return professionalDao.findByProfessionalLastName(prefix);
        } catch (DataException ex) {
            Logger.getLogger(ProfessionalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    public List<Professional> searchAllProsBySpecialtyType(SpecialtyType type) {
        try {
            return professionalDao.findByProfessionalSpecialty(type);
        } catch (DataException ex) {
            Logger.getLogger(ProfessionalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
}
