/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.Make;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface MakeService {
    public Make createMake(String makeName, int userId) throws DataValidationError;
    
    public Make readMakeById(int id);
    
    public List<Make> getAllMakes();

    public void updateMake(String makeName, int makeId) throws DataValidationError;
}
