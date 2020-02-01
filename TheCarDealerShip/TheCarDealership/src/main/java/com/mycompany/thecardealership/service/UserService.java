/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.User;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface UserService {
    public User createUser(String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;
    public User createUserWithProfile(Profile profile, String role, String password) throws NeedContactNameError, NeedContactDetailsError;
    public List<User> readAllUsers();
    public User readUserById(int id);
    public void updateUser(int userId, String firstName, String lastName, String phone, String email, String role, String password1, String password2) throws PasswordsNotMatchingError, NeedContactNameError, NeedContactDetailsError;

    public User getCurrentUser();

    public User loginUser(String email, String password);

    public User logOutUser();

    public User getUserLoggedIn();

    public void updateUserPassword(User user);
}
