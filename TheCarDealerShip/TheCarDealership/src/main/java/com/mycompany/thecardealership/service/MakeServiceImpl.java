/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.MakeDao;
import com.mycompany.thecardealership.entity.Make;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class MakeServiceImpl implements MakeService {
    MakeDao makeDao;
    
    @Autowired
    public MakeServiceImpl(MakeDao makeDao) {
        this.makeDao = makeDao;
    }
    
    @Override
    public Make createMake(String makeName, int userId) throws DataValidationError {
        if (makeName.equals("")) {
            throw new DataValidationError();
        }
        Make newMake = makeDao.createMake(makeName, userId);
        return newMake;
    }
    
    @Override
    public List<Make> getAllMakes() {
        return makeDao.readAllMakes();
    }

    @Override
    public Make readMakeById(int id) {
        return makeDao.readMakeById(id);
    }

    @Override
    public void updateMake(String makeName, int makeId) throws DataValidationError {
        if (makeName.equals("")) {
            throw new DataValidationError();
        }
        Make make = new Make();
        make.setMakeId(makeId);
        make.setMakeName(makeName);
        makeDao.updateMake(make);
    }
    
}
