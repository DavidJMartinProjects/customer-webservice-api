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
    private CustomerMapper customerMapper;

    public List<Customer> getCustomers() {
        return customerDao.findAllCustomers()
            .stream()
            .map(customerMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<Customer> saveCustomers(List<Customer> customers) {
        List<CustomerEntity> entities =
            customers.stream()
                .map(customerMapper::toEntity)
                .collect(Collectors.toList());
        return customerMapper.toDtos(customerDao.saveAll(entities));
    }

    public Customer findCustomerById(long id) {
        return customerMapper.toDto(customerDao.findCustomerById(id));
    }

    public Customer updateCustomerById(Customer customer) {
        final CustomerEntity customerEntity = customerMapper.toEntity(customer);
        final CustomerEntity updatedCustomer = customerDao.updateCustomerById(customerEntity);
        return customerMapper.toDto(updatedCustomer);
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteCustomerById(id);
    }

}
