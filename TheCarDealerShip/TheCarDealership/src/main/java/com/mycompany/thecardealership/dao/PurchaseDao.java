/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Inventory;
import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.mycompany.thecardealership.entity.Profile;

/**
 *
 * @author Eddy
 */
public interface PurchaseDao {
    //CRUD methods
    public Purchase createPurchase(Profile profile, int vehicleId, BigDecimal salePrice, String purchaseType, int userId);
    public List<Purchase> readAllPurchases();
    public Purchase readPurchaseById(int id);
    public void updatePurchase(Purchase purchase);
    public void deletePurchase(int id);
    
    // inventory
    public List<User> getGroupSalesReport(LocalDate startingOn, LocalDate to);
    public List<User> getUserSalesReport(int id, LocalDate startingOn, LocalDate to);

    public List<Inventory> runNewInventoryReport();
    public List<Inventory> runUsedInventoryReport();

    public Purchase purchaseVehicle(Purchase purchase);
    
}
