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
import com.mycompany.thecardealership.service.UserService;
import com.mycompany.thecardealership.service.ProfileService;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.service.NeedContactDetailsError;
import com.mycompany.thecardealership.service.NeedContactNameError;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 *
 * @author Eddy
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MakeDaoTest {
    
    @Autowired
    MakeDao makeDao;
    @Autowired
    UserService userService;
    @Autowired
    ProfileService profileService;
    
    public MakeDaoTest() {
    }

    @Test
    public void testCreateMake() throws NeedContactNameError, NeedContactDetailsError {
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        
        
        Make testMake = new Make();
        testMake.setCreatedBy(user);
        testMake.setMakeName(testName);
        
        
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        //Assert
        assertEquals(testMake.getMakeName(), makeCreated.getMakeName());
        assertNotEquals(testMake.getMakeId(), makeCreated.getMakeId());
    }

    @Test
    public void testReadAllMakes() throws NeedContactNameError, NeedContactDetailsError {
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        Make makeRetrieved = makeDao.readMakeById(makeCreated.getMakeId());
        
        //Assert
        assertEquals(makeRetrieved.getMakeName(), makeCreated.getMakeName());
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }

    @Test
    public void testReadMakeById() throws NeedContactNameError, NeedContactDetailsError {
        String testName = "";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        
        Make makeRetrieved = makeDao.readMakeById(makeCreated.getMakeId());
        
        //Assert
        assertEquals(makeRetrieved.getMakeName(), makeCreated.getMakeName());
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
        
    }

    @Test
    public void testUpdateMake() throws NeedContactNameError, NeedContactDetailsError {
        
        String testName = "testName";
        Profile profile = profileService.createProfile("name", "email", "phone");
        String role = "role", password = "password";
        User user = userService.createUserWithProfile(profile, role, password);
        Make makeCreated = makeDao.createMake(testName, user.getUserId());
        makeCreated.setMakeName("test");
        
        makeDao.updateMake(makeCreated);
        Make makeRetrieved = makeDao.readMakeById(makeCreated.getMakeId());
        
        //Assert
        assertEquals(makeRetrieved.getCreatedBy().getUserId(), makeCreated.getCreatedBy().getUserId());
        assertNotEquals(makeRetrieved.getMakeName(), testName);
        assertEquals(makeRetrieved.getMakeName(), "test");
        assertEquals(makeRetrieved.getMakeId(), makeCreated.getMakeId());
    }
    
}
