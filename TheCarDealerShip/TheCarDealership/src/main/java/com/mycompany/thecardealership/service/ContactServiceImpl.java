/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.ContactDao;
import com.mycompany.thecardealership.entity.Contact;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class ContactServiceImpl implements ContactService {

    ContactDao contactDao;
    ProfileService profileService;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao, ProfileService profileService) {
        this.contactDao = contactDao;
        this.profileService = profileService;
    }

    @Override
    public Contact makeContact(String name, String email, String phone, String message)
            throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        
        Profile profile = profileService.createProfile(name, email, phone);
        if (message.equals("")) {
            throw new NeedContactMessageError();
        }
        Contact contact = contactDao.createContact(profile, message);
        return contact;

    }
}
