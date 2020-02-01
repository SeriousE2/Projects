/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;

import com.mycompany.thecardealership.entity.Special;
import com.mycompany.thecardealership.service.DataValidationError;
import com.mycompany.thecardealership.service.SpecialService;
import java.util.List;
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
@RequestMapping("/specials/")
public class SpecialRESTController {

    SpecialService service;

    public SpecialRESTController(SpecialService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Special> createSpecial(String title,
            String description, int vehicleId, int userId) throws DataValidationError {
        
        Special special = null;
        try {
            special = service.createSpecial(title, description, vehicleId, userId);
        } catch(DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(special);
    }
    
    @GetMapping("/readAll")
    public List<Special> getAllSpecials() {
        return service.getAllSpecials();
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Special> getSpecialById(int id) {
        Special special = service.getSpecialById(id);
        if (special == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(special);
    }
    
    @PutMapping("/update")
    public void updateSpecial(Special special) {
        service.updateSpecial(special);
    }
    
    @PostMapping("/delete")
    public void deleteSpecial(int id) {
        service.deleteSpecial(id);
    }
}
