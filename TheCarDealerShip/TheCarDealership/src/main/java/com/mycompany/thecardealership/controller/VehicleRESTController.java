/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;

import com.mycompany.thecardealership.entity.QueryVehicle;
import com.mycompany.thecardealership.entity.Vehicle;
import com.mycompany.thecardealership.service.DataValidationError;
import com.mycompany.thecardealership.service.TooManyMilesToBeNewError;
import com.mycompany.thecardealership.service.VehicleService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/vehicles/")
public class VehicleRESTController {

    VehicleService service;

    public VehicleRESTController(VehicleService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<Vehicle> createVehicle(int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription,
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrpString, String listPriceString, int userId) throws TooManyMilesToBeNewError, DataValidationError {
        Vehicle vehicle = null;
        try {
            vehicle = service.createVehicle(makeId, modelId, mileage, year, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrpString, listPriceString, userId);
        } catch (TooManyMilesToBeNewError | DataValidationError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(vehicle);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/build")
    public ResponseEntity<Vehicle> buildVehicle(@RequestBody Vehicle vehicle) {

        vehicle = service.buildVehicle(vehicle);

        return ResponseEntity.ok(vehicle);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/readAll")
    public List<Vehicle> readAllVehicles() {
        return service.readAllVehicles();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/featured")
    public List<Vehicle> readFeaturedVehicles() {
        return service.readFeaturedVehicles();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/readOne")
    public ResponseEntity<Vehicle> readVehicleById(int vehicleId) {
        Vehicle vehicle = service.readVehicleById(vehicleId);
        if (vehicle == null) {
            return new ResponseEntity(new Error(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vehicle);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/purchaseThis")
    public boolean purchaseThis(int id) {
        service.markAsSold(id);
        return true;
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/vehiclesFiltered")
    public List<Vehicle> query20VehiclesWithFilters(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear) {
        return service.query20VehiclesWithFilters(query, type, minPrice, maxPrice, minYear, maxYear);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/vehiclesQuery")
    public List<Vehicle> query20VehiclesWithFilters(QueryVehicle data) {
        List<Vehicle> vehicles = service.query20(data);
        return vehicles;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update")
    public void updateVehicle(int vehicleId, int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription,
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrp, String listPrice, String isFeatured, int userId) throws TooManyMilesToBeNewError, DataValidationError {
        service.updateVehicle(vehicleId, makeId, modelId, mileage, year, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, msrp, listPrice, isFeatured, userId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void editVehicle(@RequestBody Vehicle vehicle) {
        service.editVehicle(vehicle);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/availableVehicles")
    public List<Vehicle> getAvailableVehicles( ) {
        List<Vehicle> vehicles = service.getAvailableVehicles();
        return vehicles;
    }

}
