/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NotFoundException;
import com.mycompany.vendingmachine.service.OutOfStockException;
import com.mycompany.vendingmachine.service.VendingMachineItemsService;
import com.mycompany.vendingmachine.ui.UserIO;
import com.mycompany.vendingmachine.ui.UserIOConsoleImpl;
import com.mycompany.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Eddy
 */


public class App {
   
    public static void main(String[] args) throws NotFoundException, OutOfStockException, InsufficientFundsException {     
    UserIO myIo = new UserIOConsoleImpl();
    VendingMachineView myView = new VendingMachineView(myIo);
    VendingMachineDao myDao = new VendingMachineDaoFileImpl("inventory.txt");
    VendingMachineItemsService myService = new VendingMachineItemsService(myDao);
    VendingMachineController controller = new VendingMachineController(myView, myService);
    controller.run();
    }   
}
