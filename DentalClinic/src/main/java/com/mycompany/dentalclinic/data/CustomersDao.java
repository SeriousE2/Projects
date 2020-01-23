/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import java.util.List;
import com.mycompany.dentalclinic.models.Customer;
import java.time.LocalDate;

/**
 *
 * @author Eddy
 */
public interface CustomersDao {
    
    void addCustomer (Customer customer) throws DataException;
    
    List<Customer> findAllCustomers() throws DataException;
    
    List<Customer> findCustomerByFristName(String firstName) throws DataException; // ???
    
    List<Customer> findCustomerByLastName(String lastName) throws DataException;
    
    List<Customer> findCustomerByDateOfBirth(LocalDate getDateOfBirth) throws DataException;
    
     Customer findCustomerByID(int customerId) throws DataException;
    

}
