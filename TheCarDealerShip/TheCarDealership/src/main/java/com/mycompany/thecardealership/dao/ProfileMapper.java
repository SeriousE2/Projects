/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class ProfileMapper implements RowMapper<Profile>{

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        
        profile.setFullName(rs.getString("fullName"));
        profile.setEmail(rs.getString("email"));
        profile.setNumber(rs.getString("phone"));
        profile.setStreetAddress(rs.getString("streetAddress"));
        profile.setZipcode(rs.getString("zipcode"));
        profile.setProfileId(rs.getInt("id"));
        
        return profile;
    }
    
}
