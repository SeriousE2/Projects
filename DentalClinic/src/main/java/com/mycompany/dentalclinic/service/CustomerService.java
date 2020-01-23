/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.service;

import com.mycompany.dentalclinic.data.CustomersDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Customer;
import com.mycompany.dentalclinic.ui.Validations;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddy
 */
public class CustomerService {

    private CustomersDao customersDao;

    public CustomerService(CustomersDao customersDao) {
        this.customersDao = customersDao;
    }

//    public boolean findByProfessionalLastName(String lastName) {
//        
//        try {
//            return professionalFileDao.findByProfessionalLastName(lastName) != null;
//        } catch (DataException ex) {
//            return false;
//        }
//    }
    public Response add(Customer customer) {
        Response response = new Response();
        Customer customerFromService;
        try {
            customerFromService = customersDao.findAllCustomers().stream()
                    .filter(c -> c.equals(customer))
                    .findFirst()
                    .orElse(null);
            if (customerFromService != null) {
                response.addError("Customer already exist.");
            }
        } catch (DataException ex) {
            response.addError("DATA ERROR");
        }
        if (!customerDuplicationCheck(customer)) {
            response.addError("Customer already exist!");
        }
        if (Validations.isNullOrWhitespace(customer.getFirstName())) {
            response.addError("First name is required.");
        }
        if (Validations.isNullOrWhitespace(customer.getLastName())) {
            response.addError("Last name is required.");
        }

        if (!response.hasError()) {
            try {
                int newCustomerId = customersDao.findAllCustomers().size() + 1;
                customer.setCustomerId(newCustomerId);
                customersDao.addCustomer(customer);
            } catch (DataException ex) {
                response.addError("DATA ERROR");
            }
        }

        return response;
    }

    public List<Customer> findByLastNameStartingWith(String prefix) {
        try {
            return customersDao.findCustomerByLastName(prefix);
        } catch (DataException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        try {
            return customersDao.findAllCustomers();
        } catch (DataException ex) {
            Logger.getLogger(CustomerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public boolean customerDuplicationCheck(Customer customer) {
        Response response = new Response();
        boolean duplicate = true;
        List<Customer> allCustomers;
        try {
            allCustomers = customersDao.findAllCustomers();
        } catch (DataException ex) {
            return response.hasError();
        }
        for (Customer c : allCustomers) {
            if (customer.getFirstName().equalsIgnoreCase(c.getFirstName())
                    && customer.getLastName().equalsIgnoreCase(c.getLastName())
                    && customer.getDateOfBirth().equals(c.getDateOfBirth())) {
                 duplicate = false;
            }
        }
        return duplicate;
    }
}

