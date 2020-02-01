/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class VehicleMapper implements RowMapper<Vehicle>{

    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        Make make = new Make();
        Model model = new Model();
        User user = new User();
        
        vehicle.setVehicleId(rs.getInt("vehicleId"));
        make.setMakeId(rs.getInt("vehicle.makeId"));
        model.setId(rs.getInt("modelId"));
        user.setUserId(rs.getInt("vehicle.userId"));
        
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setCreatedBy(user);
        
        vehicle.setVehicleYear(rs.getInt("vehicleYear"));
        vehicle.setMileage(rs.getInt("mileage"));
        vehicle.setVehicleType(rs.getString("vehicleType"));
        vehicle.setVin(rs.getString("vin"));
        vehicle.setMsrp(rs.getBigDecimal("msrp"));
        vehicle.setListPrice(rs.getBigDecimal("listPrice"));
        vehicle.setExteriorColor(rs.getString("exteriorColor"));
        vehicle.setInteriorColor(rs.getString("interiorColor"));
        vehicle.setTransmission(rs.getString("transmission"));
        vehicle.setVehicleDescription(rs.getString("vehicleDescription"));
        vehicle.setIsAvailable(rs.getBoolean("isAvailable"));
        vehicle.setBodyStyle(rs.getString("bodyStyle"));
        vehicle.setIsFeatured(rs.getBoolean("isFeatured"));
        vehicle.setImage(rs.getString("image"));
        vehicle.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
        
        return vehicle;
    }
    
}
