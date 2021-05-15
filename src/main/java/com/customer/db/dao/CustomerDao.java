package com.customer.db.dao;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.db.dao.mapper.CustomerMapper;
import com.customer.db.model.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class CustomerDao implements DbOperation<Customer> {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    // <-- CRUD -->
    @Override
    public List<Customer> findAllCustomers() {
        log.info("fetching customers.");
        return customerRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Customer findCustomerById(long id) {
        log.info("fetching customer with id: {}.", id);
        return customerRepository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(
                () -> new ResourceNotFoundException(format("resource with id: %s not found.", id))
            );
    }

    @Override
    public Customer save(Customer customer) {
        log.info("saving customer with lastName: {}.", customer.getLastName());
        CustomerEntity customerEntity = customerRepository.save(mapper.toEntity(customer));
        return mapper.toDto(customerEntity);
    }

    @Override
    public Customer updateCustomerById(Customer customer) {
        log.info("updating customer with id: {}.", customer.getId());
        CustomerEntity customerEntity = customerRepository.save(mapper.toEntity(customer));
        return mapper.toDto(customerEntity);
    }

    @Override
    public void deleteCustomerById(long id) {
        log.info("deleting customer with id: {}.", id);
        customerRepository.deleteById(id);
    }

    // <-- Queries -->
    @Override
    public boolean isEmailRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}