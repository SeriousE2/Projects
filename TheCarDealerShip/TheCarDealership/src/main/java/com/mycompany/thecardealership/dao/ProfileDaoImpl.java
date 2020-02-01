/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eddy
 */
@Repository
public class ProfileDaoImpl implements ProfileDao {
    JdbcTemplate jdbc;
    
    @Autowired
    public ProfileDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Profile createProfile(String name, String email, String phone) {
        Profile profile = new Profile();
        profile.setFullName(name);
        profile.setEmail(email);
        profile.setNumber(phone);
        
        final String CREATE_PROFILE = "INSERT INTO personProfile(fullName, email, phone) VALUES(?,?,?)";
        jdbc.update(CREATE_PROFILE, name, email, phone);
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        profile.setProfileId(newId);
        
        return profile;
    }

    @Override
    public Profile createCustomerProfile(Profile profile) {
        final String CREATE_PROFILE = "INSERT INTO personProfile(fullName, email, phone, streetAddress, zipcode) VALUES(?,?,?, ?, ?)";
        jdbc.update(CREATE_PROFILE, profile.getFullName(), profile.getEmail(), profile.getNumber(), profile.getStreetAddress(), profile.getZipcode());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        profile.setProfileId(newId);
        
        return profile;
    }

    @Override
    public List<Profile> readAllProfiles() {
        final String READ_ALL_PROFILES = "SELECT * FROM personProfile";
        List<Profile> profiles = jdbc.query(READ_ALL_PROFILES, new ProfileMapper());
        return profiles;
    }

    @Override
    public Profile readProfileById(int id) {
        Profile profile = null;
        final String READ_PROFILE_BY_ID = "SELECT * FROM personProfile WHERE id = ?";
        try {
            profile = jdbc.queryForObject(READ_PROFILE_BY_ID, new ProfileMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return profile;
    }

    @Override
    public void updateProfile(Profile profile) {
        final String UPDATE_PROFILE = "UPDATE personProfile SET fullName = ?, email = ?, phone = ?, streetAddress = ?, zipcode = ? WHERE id = ?";
        jdbc.update(UPDATE_PROFILE, profile.getFullName(), profile.getEmail(), profile.getNumber(), profile.getStreetAddress(), profile.getZipcode(), profile.getProfileId());
    }

    @Override
    public void deleteProfile(int id) {
        final String DELETE_PROFILE = "DELETE * FROM personProfile WHERE id = ?";
        jdbc.update(DELETE_PROFILE, id);
    }
    
}
