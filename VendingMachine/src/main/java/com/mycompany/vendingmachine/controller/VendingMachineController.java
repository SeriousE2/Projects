/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.dao.DataException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VendingMachineItems;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NotFoundException;
import com.mycompany.vendingmachine.service.OutOfStockException;
import com.mycompany.vendingmachine.service.VendingMachineItemsService;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddy
 */
public class VendingMachineController {
    
    
    
    private final VendingMachineView view;
    private final VendingMachineItemsService service;

    public VendingMachineController(VendingMachineView view, VendingMachineItemsService service) {
        this.view = view;
        this.service = service;
    }
    
   public void run() {
 
   int selection = 0;
   do {
       List<VendingMachineItems> all = service.getAllItems();
       view.displayItems(all);
       
       selection = view.printMenuAndGetSelection();
       switch (selection) {
           case 1:
               buyItem();
               break;
       }
   } while (selection > 0);
   view.sayGoodbye();
   
   }


    private void buyItem() {
        boolean hasErrors = false;
        
        do{
            BigDecimal addFunds = view.addFunds();
            int selectItem = view.selectItem();
            try {
                 BigDecimal changeMath = service.purchase(selectItem, addFunds);
                returnChangeMath(changeMath);
                hasErrors = false;
            } catch(DataException ex) {
                view.errorMessage(ex);
            } catch (NotFoundException ex) {
                view.errorMessage(ex);
            } catch (OutOfStockException ex) {
                view.errorMessage(ex);
            } catch (InsufficientFundsException ex) {
                view.errorMessage(ex);
            } 
        } while (hasErrors);
    }

    private void returnChangeMath(BigDecimal money) {
        Change change = new Change(money);
        int quarters = change.getQuarters();
        int dimes = change.getDimes();
        int nickels = change.getNickels();
        int pennies = change.getPennies();
        
        view.changeToDisplay(quarters, dimes, nickels, pennies);   
    }
    
}
