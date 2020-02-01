/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.PurchaseDao;
import com.mycompany.thecardealership.entity.Inventory;
import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    PurchaseDao purchaseDao;
    ProfileService profileService;
    VehicleService vehicleService;
    UserService userService;

    /**
     *
     * @param purchaseDao
     * @param profileService
     * @param vehicleService
     */
    @Autowired
    public PurchaseServiceImpl(PurchaseDao purchaseDao, ProfileService profileService, VehicleService vehicleService, UserService userService) {
        this.purchaseDao = purchaseDao;
        this.profileService = profileService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }
    
    

    @Override
    public Purchase purchaseVehicle(Purchase purchase) {
        Profile profile = profileService.createCustomerProfile(purchase.getCustomerProfile());
        purchase.setCustomerProfile(profile);
        vehicleService.markAsSold(purchase.getVehicle().getVehicleId());
        return purchaseDao.purchaseVehicle(purchase);
    }

    @Override
    public Purchase createPurchase(int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePriceString, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        // create customer profile
        Profile profile = profileService.createProfile(customerName, email, customerPhone, street1 + " " + street2, zipcode);

        // update vehicle - mark as unavailable
        vehicleService.markAsSold(vehicleId);

        BigDecimal salePrice = null;
        try {
            salePrice = new BigDecimal(salePriceString);
        } catch (NumberFormatException e) {

        }

        // add purchase to DB
        Purchase purchase = purchaseDao.createPurchase(profile, vehicleId, salePrice, purchaseType, userId);
        return purchase;
    }

    @Override
    public List<Purchase> readAllPurchases() {
        return purchaseDao.readAllPurchases();
    }

    @Override
    public Purchase readPurchaseById(int id) {
        return purchaseDao.readPurchaseById(id);
    }

    @Override
    public void updatePurchase(int purchaseId, int vehicleId, String customerName, String customerPhone, String email, String street1, String street2, String City, String State, String zipcode, String salePrice, String purchaseType, int userId) throws NeedContactNameError, NeedContactDetailsError {
        Profile profile = profileService.createProfile(customerName, email, customerPhone, street1 + " " + street2, zipcode);

        Purchase purchaseToUpdate = purchaseDao.readPurchaseById(purchaseId);

        purchaseToUpdate.setSaleType(purchaseType);

        purchaseDao.updatePurchase(purchaseToUpdate);
    }

    @Override
    public void deletePurchase(int id) {
        purchaseDao.deletePurchase(id);
    }
    
    @Override
    public List<User> getSalesReport(int id, String startingOnString, String toString) throws DataValidationError {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate startingOn = null, to = null;

        // Check the Date and set it if not done so explicitly
        if (startingOnString == null) {
            // Start of business - could be epoch or min
            startingOn = LocalDate.ofYearDay(2000, 1);
        } else {
            try {
                startingOn = LocalDate.parse(startingOnString, formatter);
            } catch (DateTimeParseException e) {
                throw new DataValidationError();
            }
        }

        if (toString == null) {
            to = LocalDate.ofYearDay(2050, 1);
        } else {
            try {
                to = LocalDate.parse(toString, formatter);
            } catch (DateTimeParseException e) {
                throw new DataValidationError();
            }
        }
        List<User> userSales;

        if (id == 0) {
            userSales = purchaseDao.getGroupSalesReport(startingOn, to);
        } else {
            userSales = purchaseDao.getUserSalesReport(id, startingOn, to);
        }

        // get userProfile with User Sales
        List<User> userNoSales = userService.readAllUsers();

        userNoSales.stream().forEach(userInfo -> {
            userSales.stream().forEach(userSale -> {
                if (userSale.getUserId() == userInfo.getUserId()) {
                    userSale.setProfile(userInfo.getProfile());
                    userSale.setRole(userInfo.getRole());
                }
            });
        });

        return userSales;
    }

    @Override
    public List<Inventory> runNewInventoryReport() {
        return purchaseDao.runNewInventoryReport();
    }
    
    @Override
    public List<Inventory> runUsedInventoryReport() {
        return purchaseDao.runUsedInventoryReport();
    }

}
