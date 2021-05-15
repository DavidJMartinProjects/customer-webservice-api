package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.DbOperations;
import com.customer.service.validation.CustomerValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class CustomerService {

    @Autowired
    private DbOperations<Customer> dbOperations;

    @Autowired
    private CustomerValidator customerValidator;

    public List<Customer> getCustomers() {
        log.info("processing request to fetch all customers.");
        return dbOperations.findAllCustomers();
    }

    public Customer saveCustomer(Customer customer) {
        log.info("processing request to save customer.");
        customerValidator.validate(customer);
        return dbOperations.save(customer);
    }

    public Customer findCustomerById(long id) {
        log.info("processing request to fetch customer with id:{}.", id);
        return dbOperations.findCustomerById(id);
    }

    public Customer updateCustomerById(Customer customer) {
        log.info("processing request to update customer with id:{}.", customer.getId());
        return dbOperations.updateCustomerById(customer);
    }

    public void deleteCustomerById(long id) {
        log.info("processing request to delete customer with id:{}.", id);
        dbOperations.deleteCustomerById(id);
    }

}
