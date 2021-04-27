package com.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {
        return customerDao.findAllCustomers()
            .stream()
            .map(customerEntity -> new ModelMapper().map(customerEntity, Customer.class))
            .collect(Collectors.toList());
    }

    public Customer findCustomerById(long id) {
        return new ModelMapper().map(customerDao.findCustomerById(id), Customer.class);
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteCustomerById(id);
    }

}
