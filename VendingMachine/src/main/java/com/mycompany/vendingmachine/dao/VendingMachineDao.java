/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachineItems;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface VendingMachineDao {

    List<VendingMachineItems> getAllItems();

    VendingMachineItems getItemById(int vendingItemsId);

    boolean update(VendingMachineItems v) throws DataException;
    
}
