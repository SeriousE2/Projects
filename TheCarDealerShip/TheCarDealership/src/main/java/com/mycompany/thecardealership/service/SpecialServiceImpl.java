/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.SpecialDao;
import com.mycompany.thecardealership.entity.Special;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class SpecialServiceImpl implements SpecialService{
    SpecialDao specialDao;
    
    @Autowired
    public SpecialServiceImpl(SpecialDao specialDao) {
        this.specialDao = specialDao;
    }

    @Override
    public Special createSpecial(String title, String description, int vehicleId, int userId) throws DataValidationError {
        if (title.equals("") || description.equals("")) {
            throw new DataValidationError();
        }
        return specialDao.createSpecial(title, description, vehicleId, userId);
    }

    @Override
    public List<Special> getAllSpecials() {
        return specialDao.readAllSpecials();
    }

    @Override
    public Special getSpecialById(int id) {
        return specialDao.readSpecialById(id);
    }
    
    @Override
    public void updateSpecial(Special special) {
        specialDao.updateSpecial(special);
    }

    @Override
    public void deleteSpecial(int id) {
        specialDao.deleteSpecial(id);
    }
}
