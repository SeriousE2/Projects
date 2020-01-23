/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VendingMachineItems;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private ArrayList<VendingMachineItems> vendingMachineItems;
    private final String FILE_PATH;

    public VendingMachineDaoFileImpl(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }
   
    @Override
    public List<VendingMachineItems> getAllItems() {
        try {
            load();
            return new ArrayList<>(vendingMachineItems);
        } catch (DataException ex) {
            return new ArrayList<>();
        }

    }

    @Override
    public VendingMachineItems getItemById(int vendingItemsId) {
        return getAllItems().stream()
                .filter(v -> v.getItemId() == vendingItemsId)
                .findAny()
                .orElse(null);
    }

    private void load() throws DataException {

        if (vendingMachineItems != null) {
            return;
        }

        vendingMachineItems = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                VendingMachineItems vendingMachineItem = unmarshall(line);
                if (vendingMachineItem != null) {
                    vendingMachineItems.add(vendingMachineItem);
                }
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

    @Override
    public boolean update(VendingMachineItems v) throws DataException {
        try {
            load();
        } catch (DataException ex) {
        }
        
        for (int i = 0; i < vendingMachineItems.size(); i++) {
            if (vendingMachineItems.get(i).getItemId() == v.getItemId()) {
                vendingMachineItems.set(i, v);
                write();
                return true;
            }
        }
        return false;
    }

    private void write() throws DataException {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            
            writer.print("Vending,Machine,Inventory");

            for (VendingMachineItems v : vendingMachineItems) {
                String line = marshall(v);
                writer.println(line);
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
        
    }

    private VendingMachineItems unmarshall(String line) {
        String[] tokens = line.split("::");
        if (tokens.length != 4) {
            return null;
        }
        VendingMachineItems vendingMachineItems = new VendingMachineItems();
        vendingMachineItems.setItemId(Integer.parseInt(tokens[0]));
        vendingMachineItems.setItemName(tokens[1]);
        vendingMachineItems.setItemCost(new BigDecimal(tokens[2]));
        vendingMachineItems.setNumberOfItemsInInventory(Integer.parseInt(tokens[3]));
        return vendingMachineItems;
    }
    
    private String marshall(VendingMachineItems v) {
        return String.format("%s::%s::%s::%s",
                v.getItemId(),
                v.getItemName(),
                v.getItemCost(),
                v.getNumberOfItemsInInventory()
        );
    }

}
