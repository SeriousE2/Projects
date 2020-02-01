/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.service.DataValidationError;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface ModelDao {
    //CRUD methods
    public Model createModel(int makeId, String modelName, int userId) throws DataValidationError;
    public List<Model> readAllModels();
    public Model readModelById(int id);
    public void updateModel(Model model);

    public List<Model> readAllModelsByMakeId(int makeId);

    public Model readModelByName(String modelName);
}
