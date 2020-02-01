/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.Inventory;
import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface PurchaseService {
    public Purchase createPurchase(int vehicleId, String customerName,
            String customerPhone, String email, String street1, String street2,
            String City, String State, String zipcode, String salePrice, String purchaseType, int userId)
            throws NeedContactNameError, NeedContactDetailsError;
    
    public List<Purchase> readAllPurchases();
    public Purchase readPurchaseById(int id);
    public void updatePurchase(int purchaseId, int vehicleId,
            String customerName, String customerPhone, String email,
            String street1, String street2, String City, String State,
            String zipcode, String salePrice, String purchaseType, int userId)
            throws NeedContactNameError, NeedContactDetailsError;
    
    public void deletePurchase(int id);
    
    // inventory
    public List<User> getSalesReport(int id, String startingOn, String to) throws DataValidationError ;
    public List<Inventory> runNewInventoryReport();
    public List<Inventory> runUsedInventoryReport();

    public Purchase purchaseVehicle(Purchase purchase);
}
