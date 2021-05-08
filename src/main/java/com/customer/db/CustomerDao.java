package com.customer.db;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.mapper.CustomerMapper;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class CustomerDao {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        log.info("fetching customers.");
        return customerRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    public Customer findCustomerById(long id) {
        log.info("fetching customer with id: {}.", id);
        return customerRepository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new ResourceNotFoundException(format("resource with id: %s not found.", id)));
    }

    public Customer save(Customer customer) {
        log.info("saving customer with lastName: {}.", customer.getLastName());
        return Stream.of(customerRepository.save(mapper.toEntity(customer)))
            .map(mapper::toDto)
            .findFirst()
            .orElse(new Customer());
    }

    public Customer updateCustomerById(Customer customer) {
        log.info("updating customer with id: {}.", customer.getId());
        return Stream.of(customerRepository.save(mapper.toEntity(customer)))
            .map(mapper::toDto)
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(format("resource with id: %s not found.", customer.getId())));
    }

    public void deleteCustomerById(long id) {
        log.info("deleting customer with id: {}.", id);
        customerRepository.deleteById(id);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}
