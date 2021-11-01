package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.service.validation.RequestValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class CustomerService {

    @Autowired
    private DbOperation<Customer> dbOperation;

    @Autowired
    private RequestValidator emailValidator;

    public List<Customer> getCustomers() {
        log.info("processing request to fetch all customers.");
        return dbOperation.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        log.info("processing request to save customer.");
        emailValidator.validate(customer);
        return dbOperation.save(customer);
    }

    public Customer findCustomerById(long id) {
        log.info("processing request to fetch customer with id: {}.", id);
        return dbOperation.findById(id);
    }

    public Customer updateCustomerById(Customer customer) {
        log.info("processing request to update customer with id: {}.", customer.getId());
        return dbOperation.update(customer);
    }

    public void deleteCustomerById(long id) {
        log.info("processing request to delete customer with id: {}.", id);
        dbOperation.deleteById(id);
    }

}
