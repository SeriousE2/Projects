/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.DataPersistenceError;
import com.mycompany.thecardealership.entity.Profile;

/**
 *
 * @author Eddy
 */
public interface ProfileService {
    public Profile createProfile(String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError;
    
    public Profile createProfile(String name, String email, String phone, String address, String zipcode) throws NeedContactNameError, NeedContactDetailsError;
    
    public Profile readProfileByInt(int id) throws DataPersistenceError;
    
    public void updateProfile(int id, String name, String email, String phone) throws NeedContactNameError, NeedContactDetailsError, DataPersistenceError;

    public Profile createCustomerProfile(Profile customerProfile);
}
