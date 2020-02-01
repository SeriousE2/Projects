/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.controller;


import com.mycompany.thecardealership.entity.Contact;
import com.mycompany.thecardealership.service.ContactService;
import com.mycompany.thecardealership.service.NeedContactDetailsError;
import com.mycompany.thecardealership.service.NeedContactMessageError;
import com.mycompany.thecardealership.service.NeedContactNameError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/contact/")
public class ContactRESTController {
    ContactService service;
    
    @Autowired
    public ContactRESTController(ContactService service) {
        this.service = service;
    }
    
    @PostMapping("/create")
    public ResponseEntity<Contact> makeContact(String name, String email, String phone, String message) throws NeedContactNameError, NeedContactDetailsError, NeedContactMessageError {
        Contact contact= null;
        try {
            contact = service.makeContact(name, email, phone, message);
        } catch(NeedContactNameError | NeedContactDetailsError | NeedContactMessageError e) {
            return new ResponseEntity(new Error(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return ResponseEntity.ok(contact);
    }
}
