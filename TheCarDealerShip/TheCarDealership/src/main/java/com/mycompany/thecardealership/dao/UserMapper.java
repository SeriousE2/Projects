/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profileId"));
        user.setProfile(profile);
        
        LocalDateTime dateAdded = rs.getTimestamp("dateAdded").toLocalDateTime();
        
        user.setPassword(rs.getString("userPassword"));
        user.setRole(rs.getString("userRole"));
        user.setDateAdded(dateAdded);
        user.setUserId(rs.getInt("id"));
        
        return user;
    }
    
}
