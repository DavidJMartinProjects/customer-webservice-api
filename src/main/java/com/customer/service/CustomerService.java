package com.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
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
    private CustomerMapper mapper;

    public List<Customer> getCustomers() {
        return customerDao.findAllCustomers()
            .stream()
            .map(mapper::toCustomer)
            .collect(Collectors.toList());
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        List<CustomerEntity> entities =
            customers.stream()
                .map(e -> mapper.toCustomerEntity(e))
                .collect(Collectors.toList());

        return mapper.toCustomers(customerDao.saveAll(entities));
    }

    public Customer findCustomerById(long id) {
        return mapper.toCustomer(customerDao.findCustomerById(id));
    }

    public Customer updateCustomerById(Customer customer) {
        final CustomerEntity customerEntity = mapper.toCustomerEntity(customer);
        return mapper.toCustomer(customerDao.updateCustomerById(customerEntity));
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteCustomerById(id);
    }

}
