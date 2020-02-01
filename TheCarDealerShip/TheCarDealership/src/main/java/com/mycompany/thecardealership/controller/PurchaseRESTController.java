/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;

import com.mycompany.thecardealership.entity.Inventory;
import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.service.DataValidationError;
import com.mycompany.thecardealership.service.NeedContactDetailsError;
import com.mycompany.thecardealership.service.NeedContactNameError;
import com.mycompany.thecardealership.service.PurchaseService;
import java.lang.Thread.State;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/purchase/")
public class PurchaseRESTController {

    PurchaseService service;

    public PurchaseRESTController(PurchaseService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Purchase> createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        Purchase purchase = null;
        try {
            purchase = service.createPurchase(vehicleId, customerName, customerPhone, email, street1, street2, City, State, zipcode, salePrice, purchaseType, userId);
        } catch(NeedContactNameError | NeedContactDetailsError e) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(purchase);
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/purchase")
    public ResponseEntity<Purchase> purchaseVehicle(@RequestBody Purchase purchase) {
        Purchase vehicleMarkedAsSold = service.purchaseVehicle(purchase);
        return ResponseEntity.ok(vehicleMarkedAsSold);
    }
    
    @GetMapping("/readAll")
    public List<Purchase> readAllPurchases() {
        return service.readAllPurchases();
    }
    
    @GetMapping("/readOne")
    public ResponseEntity<Purchase> readPurchaseById(int id) {
        Purchase purchase = service.readPurchaseById(id);
        if (purchase == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(purchase);
    }
    
    @PutMapping("/update")
    public boolean updatePurchase(int purchaseId, int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        boolean isUpdated = true;
        try {
            service.updatePurchase(purchaseId, vehicleId, customerName, customerPhone, email, street1, street2, City, State, zipcode, salePrice, purchaseType, userId);
        } catch(NeedContactNameError | NeedContactDetailsError e) {
            isUpdated = false;
        }
        return isUpdated;
    }
    
    @PostMapping("/delete")
    public void deletePurchase(int id) {
        service.deletePurchase(id);
    }
    
    @GetMapping("/salesReport")
    public List<User> getSalesReport(int id, String startingOn, String to) throws DataValidationError {
        List<User> users = service.getSalesReport(id, startingOn, to);
        return users;
    }
    
    @GetMapping("/inventoryNew")
    public List<Inventory> runNewInventoryReport() {
        List<Inventory> inventory = service.runNewInventoryReport();
        return inventory;
    }
    
    @GetMapping("/inventoryUsed")
    public List<Inventory> runUsedInventoryReport() {
        List<Inventory> inventory = service.runUsedInventoryReport();
        return inventory;
    }
   
}
