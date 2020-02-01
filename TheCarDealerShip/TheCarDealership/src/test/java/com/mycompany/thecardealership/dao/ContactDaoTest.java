/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.Contact;
import org.springframework.test.context.junit.jupiter.SpringExtension;



/**
 *
 * @author Eddy
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ContactDaoTest {
    
     @Autowired
    ContactDao dao;
    
    @Autowired
    ProfileDao profileDao;
    
    public ContactDaoTest() {
    }
    
    @Test
    public void testCreateContact() {
      
        Profile profile = profileDao.createProfile("name", "email", "phone");
        String message = "TestContact";
        
        // Act 
        Contact contactCreated = dao.createContact(profile, message);
        
        // Assert
        assertEquals(contactCreated.getMessage(), message);
        assertEquals(contactCreated.getProfile().getFullName(), "name");
        assertEquals(contactCreated.getProfile().getNumber(), "phone");
        assertEquals(contactCreated.getProfile().getEmail(), "email");
    }

    @Test
    public void testReadAllContacts() {
    }

    @Test
    public void testReadContactById() {
        
        Profile profile = profileDao.createProfile("name", "email", "phone");
        String message = "TestContact";
        Contact contact1 = dao.createContact(profile, message);
        
        // Act
        Contact contactRetrieved = dao.readContactById(contact1.getContactId());
        
        // Assert
        assertEquals(contactRetrieved.getMessage(), contact1.getMessage());
        assertEquals(contactRetrieved.getProfile().getEmail(), "email");
        assertEquals(contactRetrieved.getProfile().getNumber(), "phone");
        assertEquals(contactRetrieved.getProfile().getFullName(), "name");
    } 
}
