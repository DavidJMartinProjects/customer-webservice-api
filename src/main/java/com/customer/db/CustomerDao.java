package com.customer.db;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.service.mapper.CustomerMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class CustomerDao {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        log.info("fetching customers.");
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toDto)
            .collect(Collectors.toList());
    }

    public Customer findCustomerById(@NonNull long id) {
        log.info("fetching customer with id: {}.", id);
        return customerMapper.toDto(
            customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format("resource with id: %s not found.", id)))
        );
    }

    public Customer save(Customer customer) {
        log.info("saving customer: {}.", customer);
        return customerMapper.toDto(
            customerRepository.save(customerMapper.toEntity(customer))
        );
    }

    public Customer updateCustomerById(Customer customer) {
        log.info("updating customer with id: {} to {}.", customer.getId(), customer);
        return customerMapper.toDto(
            customerRepository.save(customerMapper.toEntity(customer))
        );
    }

    public void deleteCustomerById(@NonNull long id) {
        log.info("deleting customer with id: {}.", id);
        customerRepository.deleteById(id);
    }

}
