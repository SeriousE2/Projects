/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;

import com.mycompany.thecardealership.entity.QueryVehicle;
import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.entity.Vehicle;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface VehicleDao {
    //CRUD methods
    public Vehicle createVehicle(Make make, Model model, BigDecimal msrp, BigDecimal listPrice, int mileage, int year, String vehicleType, String vehicleDescription, String image, String exteriorColor, String interiorColor, String transmission, String bodyStyle, String vin, int userId);
    public Vehicle buildVehicle(Vehicle vehicle);
    public List<Vehicle> readAllVehicles();
    public Vehicle readVehicleById(int id);
    public void updateVehicle(Vehicle vehicle);
    
    //App specific methods
    public List<Vehicle> query20VehiclesByTypePriceAndYearDescendingMSRP(String query, String type, BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear);

    public List<Vehicle> readFeaturedVehicles();

    public List<Vehicle> query20(QueryVehicle data);

    public List<Vehicle> getAvailableVehicles();
}
