/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Inventory;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class InventoryMapper implements RowMapper<Inventory>{

    @Override
    public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setCount(rs.getInt("modelCount"));
        inventory.setYear(rs.getInt("vehicleYear"));
        inventory.setMakeName(rs.getString("makeName"));
        inventory.setModelName(rs.getString("modelName"));
        inventory.setStockValue(rs.getBigDecimal("stockValue"));
        return inventory;
    }
    
}
