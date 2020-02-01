/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.ModelDao;
import com.mycompany.thecardealership.entity.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class ModelServiceImpl implements ModelService{
    ModelDao modelDao;
    MakeService makeService;
    
    @Autowired
    public ModelServiceImpl(ModelDao modelDao, MakeService makeService) {
        this.modelDao = modelDao;
        this.makeService = makeService;
    }
    
    @Override
    public Model createModel(int makeId, String modelName, int userId) throws DataValidationError {
        if (modelName.equals("")) {
            throw new DataValidationError();
        }
        return modelDao.createModel(makeId, modelName, userId) ;
    }
    
    @Override
    public List<Model> readAllModels() {
        return modelDao.readAllModels();
    }

    @Override
    public Model readModelById(int id) {
        return modelDao.readModelById(id);
    }

    @Override
    public void updateModel(int makeId, String modelName, int modelId) throws DataValidationError {
        if (modelName.equals("")) {
            throw new DataValidationError();
        }
        Model model = new Model();
        model.setId(modelId);
        model.setMake(makeService.readMakeById(makeId));
        model.setModelName(modelName);
        modelDao.updateModel(model);
    }

    @Override
    public List<Model> readAllModelsByMakeId(int makeId) {
        return modelDao.readAllModelsByMakeId(makeId);
    }

    @Override
    public Model readModelByName(String modelName) {
        return modelDao.readModelByName(modelName);
    }
}
