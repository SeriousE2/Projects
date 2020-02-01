/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;

import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.service.DataValidationError;
import com.mycompany.thecardealership.service.ModelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/model/")
public class ModelRESTController {

    ModelService service;
    
    @Autowired
    public ModelRESTController(ModelService service) {
        this.service = service;
    }
    
        @PostMapping("/create")
    public ResponseEntity<Model> createModel(int makeId, String modelName, int userId) {
        Model model = null;
        try {
            model = service.createModel(makeId, modelName, userId);
        } catch (DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Model> readModelById(int id) {
        Model model = service.readModelById(id);
        if (model == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/readAll")
    public ResponseEntity<List<Model>> getAllModels(){
        List<Model> model = service.readAllModels();
        if (model == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
    
    @GetMapping("/readAllByMakeId")
    public ResponseEntity<List<Model>> getAllModelsByMake(int makeId){
        List<Model> model = service.readAllModelsByMakeId(makeId);
        if (model == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(model);
    }
    
    @PutMapping("/update")
    public boolean updateModel(int makeId, String modelName, int modelId){
        boolean didUpdate = true;
        try {
            service.updateModel(makeId, modelName, modelId);
        } catch (DataValidationError e) {
            didUpdate = false;
        }
        return didUpdate;
    }
}
