/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.QueryVehicle;
import com.mycompany.thecardealership.entity.Vehicle;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface VehicleService {
    public Vehicle createVehicle(int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrpString, String listPriceString, int userId) throws TooManyMilesToBeNewError, DataValidationError;
    
    public Vehicle buildVehicle(Vehicle vehicle);
    
    public Vehicle readVehicleById(int vehicleId);
    
    public List<Vehicle> query20VehiclesWithFilters(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear);
    
    public void updateVehicle(int vehicleId, int makeId, int modelId, int mileage, int year, String vehicleType, String vehicleDescription, 
            String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin,
            String msrp, String listPrice, String isFeatured, int userId) throws TooManyMilesToBeNewError, DataValidationError;

    public void markAsSold(int vehicleId);
    
    public List<Vehicle> readAllVehicles();

    public List<Vehicle> readFeaturedVehicles();

    public void editVehicle(Vehicle vehicle);

    public List<Vehicle> query20(QueryVehicle data);

    public List<Vehicle> getAvailableVehicles();
}
