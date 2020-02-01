/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.dao.UserDao;
import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eddy
 */
@Service
public class UserServiceImpl implements UserService {
    UserDao userDao;
    ProfileService profileService;
    User user;
    
    @Autowired
    public UserServiceImpl(UserDao userDao, ProfileService profileService) {
        this.userDao = userDao;
        this.profileService = profileService;
    }

    @Override
    public User createUser(String firstName, String lastName, String phone,
            String email, String role, String password1, String password2)
            throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        
        if (!password1.equals(password2)) {
            throw new PasswordsNotMatchingError();
        }
        
        Profile profile = profileService.createProfile(firstName+" "+lastName, email, phone);
        User user = userDao.createUser(profile, role, password1);
        return user;
    }

    @Override
    public User createUserWithProfile(Profile profile, String role, String password)
            throws NeedContactNameError, NeedContactDetailsError {
        
        return userDao.createUser(profile, role, password);
    }

    @Override
    public List<User> readAllUsers() {
        return userDao.readAllUsers();
    }

    @Override
    public User readUserById(int id) {
        return userDao.readUserById(id);
    }

    @Override
    public void updateUser(int userId, String firstName, String lastName,
            String phone, String email, String role, String password1,
            String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError {
        
        if (!password1.equals(password2)) {
            throw new PasswordsNotMatchingError();
        }
        
        // Get user
        User user = userDao.readUserById(userId);
        
        // Update properties
        Profile profile = user.getProfile();
        profile.setEmail(email);
        profile.setFullName(firstName+" "+lastName);
        profile.setNumber(phone);
        user.setRole(role);
        user.setPassword(password2);
        
        // Update user in DB
        userDao.updateUser(user);
    }

    @Override
    public User getCurrentUser() {
        return this.user;
    }

    @Override
    public User loginUser(String email, String password) {
        User userRetrieved = userDao.readUserByNameAndPasswrod(password);
        if (userRetrieved.getProfile().getEmail().equals(email) &&
                (userRetrieved.getRole().equals("admin") || userRetrieved.getRole().equals("sales"))) {
            
            this.user = userRetrieved;
            return this.user;
        } else {
            return null;
        }
        
    }

    @Override
    public User logOutUser() {
        User user = this.user;
        this.user = null;
        return user;
    }

    @Override
    public User getUserLoggedIn() {
        return this.user;
    }

    @Override
    public void updateUserPassword(User user) {
        userDao.updateUser(user);
    }
}
