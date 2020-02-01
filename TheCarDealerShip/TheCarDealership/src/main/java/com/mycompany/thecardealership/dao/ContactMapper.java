/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;

import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Eddy
 */
public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setProfileId(rs.getInt("profileId"));
        
        Contact contact = new Contact();
        contact.setProfile(profile);
        contact.setContactId(rs.getInt("id"));
        contact.setProfile(profile);
        contact.setMessage(rs.getString("message"));
        contact.setTimePosted(rs.getTimestamp("timePosted").toLocalDateTime());
        
        return contact;
    }
    
}
