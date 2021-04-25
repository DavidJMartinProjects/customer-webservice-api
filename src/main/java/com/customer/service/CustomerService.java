package com.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;

/**
 * @author davidjmartin
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {
        return customerDao.findAllCustomers();
    }

    public Customer findCustomerById(long id) {
        return customerDao.findCustomerById(id);
    }
}
