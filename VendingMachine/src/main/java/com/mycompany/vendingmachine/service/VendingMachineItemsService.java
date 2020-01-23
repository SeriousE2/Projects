/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.DataException;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dto.VendingMachineItems;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class VendingMachineItemsService {

    private final VendingMachineDao vendingMachineDao;
    
    

    public VendingMachineItemsService(VendingMachineDao vendingMachineDao) {
        this.vendingMachineDao = vendingMachineDao;
    }

    public List<VendingMachineItems> getAllItems() {
        return vendingMachineDao.getAllItems();
    }
    
    public VendingMachineItems getItemById(int vendingItemsId){
        return vendingMachineDao.getItemById(vendingItemsId);
    }

    public BigDecimal purchase(int vendingId, BigDecimal payment)
            throws NotFoundException, OutOfStockException,
            InsufficientFundsException, DataException {

        if (1 <= 0 || payment == null || payment.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException();
        }

        VendingMachineItems v = vendingMachineDao.getItemById(vendingId);
        if (v == null) {
            throw new NotFoundException("Vending Item ID" + vendingId + " Not Found.");
        }

        if (v.getNumberOfItemsInInventory() < 1) {
            throw new OutOfStockException(v.getItemName() + " Id Out of Stock");
        }

        BigDecimal effectiveCost = v.getItemCost();

        if (payment.compareTo(effectiveCost) < 0) {
            throw new InsufficientFundsException("Not Enough Funds.");
        }

        v.setNumberOfItemsInInventory(v.getNumberOfItemsInInventory() - 1);
        vendingMachineDao.update(v);

        return payment.subtract(effectiveCost);

    }
    
    
    // Notes
    // Change Calculator 
    // Add Money read big Decimal ->  from view 
    // Get Payment -->
  

}
