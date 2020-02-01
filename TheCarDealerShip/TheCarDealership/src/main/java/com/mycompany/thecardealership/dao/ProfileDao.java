/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import java.util.List;
import com.mycompany.thecardealership.entity.Profile;

/**
 *
 * @author Eddy
 */
public interface ProfileDao {
    //CRUD methods
    public Profile createProfile(String name, String email, String phone);
    public List<Profile> readAllProfiles();
    public Profile readProfileById(int id);
    public void updateProfile(Profile profile);
    public void deleteProfile(int id);

    public Profile createCustomerProfile(Profile profile);
}
