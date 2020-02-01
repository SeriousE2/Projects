/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;

import com.mycompany.thecardealership.entity.Profile;
import com.mycompany.thecardealership.entity.Contact;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eddy
 */
@Repository
public class ContactDaoImpl implements ContactDao {

    JdbcTemplate jdbc;
    ProfileDao profileDao;

    @Autowired
    public ContactDaoImpl(JdbcTemplate jdbc, ProfileDao profileDao) {
        this.jdbc = jdbc;
        this.profileDao = profileDao;
    }

    @Override
    public Contact createContact(Profile profile, String message) {
//         Make contact object
        Contact newContact = new Contact();
        Timestamp timeStamp = Timestamp.valueOf(newContact.getTimePosted());
        newContact.setProfile(profile);
        newContact.setMessage(message);

        // Add contact details to DB
        final String CREATE_CONTACT = "INSERT INTO contact(profileId, message, timePosted) VALUES(?,?,?)";
        jdbc.update(CREATE_CONTACT, profile.getProfileId(), message, timeStamp);

        // Get id and assign it to contact object
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        newContact.setContactId(newId);
        return newContact;
    }

    @Override
    public List<Contact> readAllContacts() {
        final String READ_CONTACTS = "SELECT * FROM contact";
        List<Contact> contacts = jdbc.query(READ_CONTACTS, new ContactMapper());
        contacts.stream().forEach(contact -> {
            contact.setProfile(profileDao.readProfileById(contact.getProfile().getProfileId()));
        });

        return contacts;
    }

    @Override
    public Contact readContactById(int id) {
        final String READ_CONTACT_BY_ID = "SELECT * FROM contact WHERE id = ?";
        Contact contact = null;
        try {
            contact = jdbc.queryForObject(READ_CONTACT_BY_ID, new ContactMapper(), id);
            contact.setProfile(profileDao.readProfileById(contact.getProfile().getProfileId()));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return contact;
    }

    @Override
    public void updateContact(Contact contact) {
        final String UPDATE_CONTACT = "UPDATE contact SET message = ? WHERE id = ?";
        jdbc.update(UPDATE_CONTACT, contact.getMessage());
        profileDao.updateProfile(contact.getProfile());
    }

    @Override
    public void deleteContact(int id) {
        final String DELETE_CONTACT = "DELETE * FROM contact WHERE id = ?";
        jdbc.update(DELETE_CONTACT, id);
    }

}
