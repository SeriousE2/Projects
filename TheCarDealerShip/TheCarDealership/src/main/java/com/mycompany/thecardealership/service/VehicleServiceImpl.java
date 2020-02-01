/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.VehicleDao;
import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.entity.QueryVehicle;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Vehicle;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    VehicleDao vehicleDao;
    MakeService makeService;
    ModelService modelService;
    UserService userService;

    @Autowired
    public VehicleServiceImpl(VehicleDao vehicleDao,
            MakeService makeService, ModelService modelService, UserService userService) {
        
        this.vehicleDao = vehicleDao;
        this.makeService = makeService;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public Vehicle createVehicle(int makeId, int modelId,
            int mileage, int year, String vehicleType, String vehicleDescription,
            String image, String exteriorColor, String interiorColor, String transmission,
            String bodyStyle, String vin, String msrpString, String listPriceString, int userId)
            throws TooManyMilesToBeNewError, DataValidationError {
        
        BigDecimal msrp, listPrice;
        Make make;
        Model model;
        if (vehicleType.equals("") || bodyStyle.equals("") 
                || exteriorColor.equals("") || interiorColor.equals("") 
                || vehicleDescription.equals("") || image.equals("") || vin.equals("")) {
            
            throw new DataValidationError();
        }

        if (!transmission.equals("manual") && !transmission.equals("automatic")) {
            throw new DataValidationError();
        }

        if (vin.length() != 17 || vin.contains("O") || vin.contains("I") 
                || vin.contains("Q") || vin.contains("o") || vin.contains("i") || vin.contains("q")) {
            
            throw new DataValidationError();
        }

        if (!image.endsWith("jpg") && !image.endsWith("jepg") && !image.endsWith("png")) {
            throw new DataValidationError();
        }

        try {
            listPrice = new BigDecimal(listPriceString);
            msrp = new BigDecimal(msrpString);
            if (listPrice.compareTo(BigDecimal.ZERO) < 1 
                    || msrp.compareTo(BigDecimal.ZERO) < 1 || listPrice.compareTo(msrp) == 1) {
                
                throw new DataValidationError();
            }
            LocalDate now = LocalDate.now();
            int currentYear = now.getYear();
            if (year < 2000 || year > currentYear + 1) {
                throw new DataValidationError();
            }

            if (mileage < 0) {
                throw new TooManyMilesToBeNewError();
            }

            if (vehicleType.equals("new") && mileage > 1000) {
                throw new TooManyMilesToBeNewError();
            }

            make = makeService.readMakeById(makeId);
            model = modelService.readModelById(modelId);

        } catch (NumberFormatException e) {
            throw new DataValidationError();
        }

        Vehicle vehicle = vehicleDao.createVehicle(make, model, msrp, listPrice,
                mileage, year, vehicleType, vehicleDescription, image, exteriorColor,
                interiorColor, transmission, bodyStyle, vin, userId);
        
        return vehicle;

    }
    
    @Override
    public Vehicle buildVehicle(Vehicle vehicle) {
        Model model = modelService.readModelByName(vehicle.getModel().getModelName());
        vehicle.setModel(model);
        return vehicleDao.buildVehicle(vehicle);
    }

    @Override
    public Vehicle readVehicleById(int vehicleId) {
        return vehicleDao.readVehicleById(vehicleId);
    }
    
    @Override
    public List<Vehicle> readAllVehicles() {
        return vehicleDao.readAllVehicles();
    }

    @Override
    public List<Vehicle> readFeaturedVehicles() {
        return vehicleDao.readFeaturedVehicles();
    }

    @Override
    public List<Vehicle> query20VehiclesWithFilters(String query, String type,
            BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear) {
        return vehicleDao.query20VehiclesByTypePriceAndYearDescendingMSRP(query, type, minPrice, maxPrice, minYear, maxYear);
    }

    @Override
    public void updateVehicle(int vehicleId, int makeId, int modelId, int mileage,
            int vehicleYear, String vehicleType, String vehicleDescription, String image,
            String exteriorColor, String interiorColor, String transmission, String bodyStyle,
            String vin, String msrpString, String listPriceString, String isFeatured, int userId)
            throws TooManyMilesToBeNewError, DataValidationError {
        
        BigDecimal msrp, listPrice;
        Vehicle vehicle;

        if (vehicleType.equals("") || bodyStyle.equals("") 
                || exteriorColor.equals("") || interiorColor.equals("") 
                || vehicleDescription.equals("") || vin.equals("")) {
            
            throw new DataValidationError();
        }

        if (!transmission.equals("manual") && !transmission.equals("automatic")) {
            throw new DataValidationError();
        }

        if (vin.length() != 17 || vin.contains("O") || vin.contains("I") 
                || vin.contains("Q") || vin.contains("o") || vin.contains("i") || vin.contains("q")) {
            
            throw new DataValidationError();
        }

        if (!image.endsWith("jpg") && !image.endsWith("jepg") && !image.endsWith("png") && !image.equals("")) {
            throw new DataValidationError();
        }

        try {
            listPrice = new BigDecimal(listPriceString);
            msrp = new BigDecimal(msrpString);
        } catch (NumberFormatException e) {
            throw new DataValidationError();
        }

        if (listPrice.compareTo(BigDecimal.ZERO) < 1 || msrp.compareTo(BigDecimal.ZERO) < 1 
                || listPrice.compareTo(msrp) == 1) {
            
            throw new DataValidationError();
        }

        try {
            // Get vehicle
            vehicle = vehicleDao.readVehicleById(vehicleId);
            
            LocalDate now = LocalDate.now();
            int currentYear = now.getYear();
            if (vehicleYear < 2000 || vehicleYear > vehicle.getDateAdded().getYear()) {
                throw new DataValidationError();
            }

            if (mileage < 0) {
                throw new TooManyMilesToBeNewError();
            }

            if (vehicleType.equals("new") && mileage > 1000) {
                throw new TooManyMilesToBeNewError();
            }

        } catch (NumberFormatException e) {
            throw new DataValidationError();
        }

        // Set new properties
        vehicle.setMake(makeService.readMakeById(makeId));
        vehicle.setModel(modelService.readModelById(modelId));
        vehicle.setMileage(mileage);
        vehicle.setVehicleYear(vehicleYear);
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehicleDescription(vehicleDescription);
        if (!image.equals("")) {
            vehicle.setImage(image);
        }
        vehicle.setExteriorColor(exteriorColor);
        vehicle.setInteriorColor(interiorColor);
        vehicle.setTransmission(transmission);
        vehicle.setBodyStyle(bodyStyle);
        vehicle.setVin(vin);
        vehicle.setMsrp(msrp);
        vehicle.setListPrice(listPrice);
        if (!isFeatured.equals("")) {
            if (isFeatured.equals("true")) {
                vehicle.setIsFeatured(true);
            } else {
                vehicle.setIsFeatured(false);
            }
        }
        User currentUser = userService.readUserById(userId);
        vehicle.setCreatedBy(currentUser);

        //Update vehicle
        vehicleDao.updateVehicle(vehicle);

    }

    @Override
    public void markAsSold(int vehicleId) {
        Vehicle vehicle = vehicleDao.readVehicleById(vehicleId);
        vehicle.setIsAvailable(false);
        vehicleDao.updateVehicle(vehicle);
    }
    
    public boolean validateData(int makeId, int modelId, int mileage,
            int year, String vehicleType, String vehicleDescription,
            String image, String exteriorColor, String interiorColor,
            String transmission, String bodyStyle, String vin, String msrpString, String listPriceString) {
        
        boolean isValid = true;
        
        return isValid;
    }

    @Override
    public void editVehicle(Vehicle vehicle) {
        Model model = modelService.readModelByName(vehicle.getModel().getModelName());
        vehicle.setModel(model);
        vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public List<Vehicle> query20(QueryVehicle data) {
        return vehicleDao.query20(data);
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        return vehicleDao.getAvailableVehicles();
    }
}
