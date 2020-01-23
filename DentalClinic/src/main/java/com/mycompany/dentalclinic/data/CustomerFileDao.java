/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import com.mycompany.dentalclinic.models.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eddy
 */
public class CustomerFileDao extends FileDao<Customer> implements CustomersDao{

    public CustomerFileDao (String FILE_PATH) {
        super(FILE_PATH, 4, true);       
    }
    
    @Override
    public void addCustomer(Customer customer) throws DataException {
        append(customer, this::mapToString);
    }

    @Override
    public List<Customer> findAllCustomers() throws DataException {
        return read(this::mapToCustomer).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findCustomerByFristName(String firstName) throws DataException {
       return findAllCustomers().stream()
                .filter(c -> c.getLastName().equals(firstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findCustomerByLastName(String lastName) throws DataException {
        return findAllCustomers().stream()
                .filter(c -> c.getLastName().startsWith(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findCustomerByDateOfBirth(LocalDate getDateOfBirth) throws DataException {
        return findAllCustomers().stream()
                .filter(c -> c.getDateOfBirth().equals(getDateOfBirth))
                .collect(Collectors.toList());
    }
    
    @Override
    public Customer findCustomerByID(int customerId) throws DataException {
        return findAllCustomers().stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }


    private String mapToString(Customer customer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return String.format("%s,%s,%s,%s",
                  customer.getCustomerId(),
                  customer.getFirstName(),
                  customer.getLastName(),
                  customer.getDateOfBirth().format(formatter).toString().replace("-", "/"));
    }
    
    private Customer mapToCustomer(String[] tokens) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate date = LocalDate.parse(tokens[3], formatter);
        return new Customer(
                Integer.parseInt(tokens[0]),
                tokens[1],
                tokens[2],
                date);
    }
 
}
