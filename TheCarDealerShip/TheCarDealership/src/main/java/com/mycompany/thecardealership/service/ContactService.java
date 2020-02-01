/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.service;

import com.mycompany.thecardealership.entity.Contact;



/**
 *
 * @author Eddy
 */
public interface ContactService {
    public Contact makeContact(String name, String email, String phone, String message)
            throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError;
}
