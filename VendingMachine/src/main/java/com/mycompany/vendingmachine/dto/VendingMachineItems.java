/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author Eddy
 */
public class VendingMachineItems {
    
    private int itemId;
    private String itemName;
    private BigDecimal itemCost;
    private int numberOfItemsInInventory;
    
    public VendingMachineItems(){
    
    }

    public VendingMachineItems (int itemId, String itemName, BigDecimal itemCost, int numberOfItemsInInventory) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.numberOfItemsInInventory = numberOfItemsInInventory;
    }
      

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getNumberOfItemsInInventory() {
        return numberOfItemsInInventory;
    }

    public void setNumberOfItemsInInventory(int numberOfItemsInInventory) {
        this.numberOfItemsInInventory = numberOfItemsInInventory;
    }
}
