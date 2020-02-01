/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;

import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.service.DataValidationError;
import com.mycompany.thecardealership.service.MakeService;
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
@RequestMapping("/make/")
public class MakeRESTController {

    MakeService service;

    @Autowired
    public MakeRESTController(MakeService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Make> createMake(String makeName, int userId) throws DataValidationError {
        Make make = null;
        try {
            make = service.createMake(makeName, userId);
        } catch (DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(make);
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Make> readMakeById(int id) {
        Make make = service.readMakeById(id);
        if (make == null) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(make);
    }
    
    @GetMapping("/readAll")
    public ResponseEntity<List<Make>> getAllMakes(){
        List<Make> makes = service.getAllMakes();
        if (makes == null) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(makes);
    }
    
    @PutMapping("/update")
    public boolean updateMake(String makeName, int makeId){
        boolean didUpdate = true;
        try {
            service.updateMake(makeName, makeId);
        } catch(DataValidationError e) {
            didUpdate = false;
        }
        
        return didUpdate;
    }
}
