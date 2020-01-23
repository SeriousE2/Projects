/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.dataTest;

import com.mycompany.dentalclinic.data.CustomersDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Customer;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class CustomerFileDaoTestStub implements CustomersDao{

    @Override
    public void addCustomer(Customer customer) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findAllCustomers() throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findCustomerByFristName(String firstName) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findCustomerByLastName(String lastName) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> findCustomerByDateOfBirth(LocalDate getDateOfBirth) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findCustomerByID(int customerId) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
