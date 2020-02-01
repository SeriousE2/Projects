/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.Special;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface SpecialService {
    public Special createSpecial(String title, String description, int vehicleId, int userId) throws DataValidationError;
    public List<Special> getAllSpecials();
    public Special getSpecialById(int id);
    public void updateSpecial(Special special);
    public void deleteSpecial(int id);
}
