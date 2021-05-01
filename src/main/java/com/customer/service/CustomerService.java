package com.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import com.customer.service.mapper.CustomerMapper;
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
            .map(CustomerMapper::toCustomer)
            .collect(Collectors.toList());
    }

    public Customer findCustomerById(long id) {
        return CustomerMapper.toCustomer(customerDao.findCustomerById(id));
    }

    public Customer updateCustomerById(Customer customer) {
        CustomerEntity customerEntity = customerDao.updateCustomerById(CustomerMapper.toCustomerEntity(customer));
        return CustomerMapper.toCustomer(customerEntity);
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteCustomerById(id);
    }

}
