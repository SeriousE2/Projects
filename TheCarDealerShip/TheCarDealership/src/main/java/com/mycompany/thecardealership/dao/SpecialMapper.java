/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Special;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class SpecialMapper implements RowMapper<Special>{

    @Override
    public Special mapRow(ResultSet rs, int rowNum) throws SQLException {
        Special special = new Special();
        User user = new User();
        user.setUserId(rs.getInt("userId"));
        special.setCreatedBy(user);
        
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicleId"));
        special.setVehicle(vehicle);
        
        special.setDateAdded(rs.getTimestamp("dateAdded").toLocalDateTime());
        if (rs.getDate("dateBegin") != null) {
            special.setDateBegin(rs.getDate("dateBegin").toLocalDate());
        }
        if (rs.getDate("dateEnd") != null) {
            special.setDateEnd(rs.getDate("dateEnd").toLocalDate());
        }
        
        special.setSpecialDescription(rs.getString("specialDescription"));
        special.setTitle(rs.getString("title"));
        special.setSpecialId(rs.getInt("id"));
        
        return special;
    }
    
}
