/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.User;
import java.sql.Timestamp;
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
public class UserDaoImpl implements UserDao{
    JdbcTemplate jdbc;
    ProfileDao profileDao;
    
    @Autowired
    public UserDaoImpl(JdbcTemplate jdbc, ProfileDao profileDao) {
        this.jdbc = jdbc;
        this.profileDao = profileDao;
    }

    @Override
    public User createUser(Profile profile, String role, String password) {
        User user = new User();
        user.setPassword(password);
        user.setProfile(profile);
        user.setRole(role);
        Timestamp timeStamp = Timestamp.valueOf(user.getDateAdded());
        
        final String CREATE_USER = "INSERT INTO carDealershipUser(profileId, userRole, userPassword, dateAdded) VALUES(?,?,?,?)";
        jdbc.update(CREATE_USER, profile.getProfileId(), role, password, timeStamp);
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setUserId(newId);
        
        return user;
    }

    @Override
    public List<User> readAllUsers(){
        final String READ_USERS = "SELECT * FROM carDealershipUser";
        List<User> users = jdbc.query(READ_USERS, new UserMapper());
        users.stream().forEach(user -> {
            user.setProfile(profileDao.readProfileById(user.getProfile().getProfileId()));
            
        });
        return users;
    }

    @Override
    public User readUserById(int id) {
        final String READ_USER_BY_ID = "SELECT * FROM carDealershipUser WHERE id = ?";
        User user = null;
        try {
            user = jdbc.queryForObject(READ_USER_BY_ID, new UserMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        user.setProfile(profileDao.readProfileById(user.getProfile().getProfileId()));
        return user;
    }

    @Override
    public void updateUser(User user) {
        final String UPDATE_USER = "UPDATE carDealershipUser SET userRole = ?, userPassword = ? WHERE id = ?";
        jdbc.update(UPDATE_USER, user.getRole(), user.getPassword(), user.getUserId());
    }

    @Override
    public void deleteUser(int id) {
        final String DELETE_USER = "DELETE * FROM carDealershipUser WHERE id = ?";
        jdbc.update(DELETE_USER, id);
    }

    @Override
    public User readUserByNameAndPasswrod(String password) {
        final String READ_USER_BY_PASSWORD = "SELECT * FROM carDealershipUser WHERE userPassword = ?";
        User user = null;
        try {
            user = jdbc.queryForObject(READ_USER_BY_PASSWORD, new UserMapper(), password);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
        user.setProfile(profileDao.readProfileById(user.getProfile().getProfileId()));
        return user;
    }
    
}
