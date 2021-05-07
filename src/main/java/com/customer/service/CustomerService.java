package com.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.service.mapper.CustomerMapper;
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
    private CustomerMapper customerMapper;

    public List<Customer> getCustomers() {
        return customerDao.findAllCustomers();
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        return customerDao.saveAll(customers);
    }

    public Customer findCustomerById(long id) {
        return customerDao.findCustomerById(id);
    }

    public Customer updateCustomerById(Customer customer) {
        return customerDao.updateCustomerById(customer);
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteCustomerById(id);
    }

}
