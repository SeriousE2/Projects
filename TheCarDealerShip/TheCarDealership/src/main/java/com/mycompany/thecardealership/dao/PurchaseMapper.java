/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class PurchaseMapper implements RowMapper<Purchase>{

    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        Purchase purchase = new Purchase();
        Profile profile = new Profile();
        User user = new User();
        Vehicle vehicle = new Vehicle();
        
        profile.setProfileId(rs.getInt("profileId"));
        user.setUserId(rs.getInt("userId"));
        vehicle.setVehicleId(rs.getInt("vehicleId"));
        
        purchase.setCreatedBy(user);
        purchase.setCustomerProfile(profile);
        purchase.setVehicle(vehicle);
        
        purchase.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
        purchase.setSalePrice(rs.getBigDecimal("salePrice"));
        purchase.setSaleType(rs.getString("saleType"));
        
        return purchase;
    }
    
}
