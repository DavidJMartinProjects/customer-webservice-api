package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.mapper.CustomerMapper;
import com.customer.db.validation.CustomerValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerValidator customerValidator;

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getCustomers() {
        log.info("processing request to fetch all customers.");
        return customerDao.findAllCustomers();
    }

    public Customer saveCustomer(Customer customer) {
        log.info("processing request to save customer.");
        customerValidator.validate(customer);
        return customerDao.save(customer);
    }

    public Customer findCustomerById(long id) {
        log.info("processing request to fetch customer with id:{}.", id);
        return customerDao.findCustomerById(id);
    }

    public Customer updateCustomerById(Customer customer) {
        log.info("processing request to update customer with id:{}.", customer.getId());
        return customerDao.updateCustomerById(customer);
    }

    public void deleteCustomerById(long id) {
        log.info("processing request to delete customer with id:{}.", id);
        customerDao.deleteCustomerById(id);
    }

}
