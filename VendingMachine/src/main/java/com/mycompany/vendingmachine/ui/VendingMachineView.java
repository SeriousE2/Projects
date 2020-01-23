/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dao.DataException;
import com.mycompany.vendingmachine.dto.VendingMachineItems;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayItems(List<VendingMachineItems> all) {
        io.print("=============================");
        io.print("=====The Vending Machine=====");
        io.print("========INVENTORY LIST=======");
        io.print("=============================");
        for (VendingMachineItems item : all) {
            io.print(item.getItemName());
            io.print("Vending ID:" + item.getItemId());
            io.print("Price: " + item.getItemCost());
            io.print("Total Inventory:" + item.getNumberOfItemsInInventory());
            io.print("=============================");
        }
    }

    public int printMenuAndGetSelection() {
        io.print("========Main Menu==========");
        io.print("1. Add Funds");
        io.print("0. Exit");
        io.print("============================");

        return io.readInt("Please select from the main menu options.", 0, 1);
    }

    public BigDecimal addFunds() {
        BigDecimal fundsAdded = io.readBigDecimal("Please enter funds:");
        return fundsAdded;
    }
    
     public void sayGoodbye() {
        io.print("\nGoodbye");
    }
     
      public void errorMessage(Exception ex) {
        io.readString(ex.getMessage() + "\nPress enter to continue");
    }

    public int selectItem() {
        int itemSelection = io.readInt("Please select an Item using the Vending ID: ");
        return itemSelection;
    }

    public void changeToDisplay(int quarters, int dimes, int nickels, int pennies) {
        io.print("Thank you for your purchase! ");
        io.print("Please collect your change. ");
        io.print(quarters + "-Quarters " + dimes + "-Dimes " + nickels + "-Nickels " + pennies + "-Pennies");
        io.print("");
    }
      
    
}
