/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.dataTest;

import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.data.ProfessionalDao;
import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class ProfessionalFileDaoTestStub implements ProfessionalDao{
    
//    List<Professional> professional;
//
//    public ProfessionalFileDaoTestStub(List<Professional> professional) {
//        this.professional = professional;
//    }
    
    

    public ProfessionalFileDaoTestStub() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Professional> findAllProfessional() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professional findByProfessionalId(int professionalId) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professional> findByProfessionalFirstName(String professionalFirstName) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professional> findByProfessionalLastName(String professionalLastName) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professional> findByProfessionalSpecialty(SpecialtyType type) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Professional> findByProfessionalHourlyRate(BigDecimal hourlyRate) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Professional findByProfessionalLastNameNumberTwo(String professionalLastName) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getProfessionalLastName().equalsIgnoreCase(professionalLastName))
                .findFirst().orElse(null);
    }
    
}
