/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.User;
import java.util.List;
import com.mycompany.thecardealership.entity.Profile;

/**
 *
 * @author Eddy
 */
public interface UserDao {
    //CRUD methods
    public User createUser(Profile profile, String role, String password);
    public List<User> readAllUsers();
    public User readUserById(int id);
    public void updateUser(User user);
    public void deleteUser(int id);

    public User readUserByNameAndPasswrod(String password);
}
