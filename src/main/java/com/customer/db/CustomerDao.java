package com.customer.db;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ResourceNotFoundException;
import com.customer.service.mapper.CustomerMapper;
import lombok.NonNull;

/**
 * @author davidjmartin
 */
@Component
public class CustomerDao {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerMapper::toDto)
            .collect(Collectors.toList());
    }

    public Customer save(Customer customer) {
        return customerMapper.toDto(
            customerRepository.save(customerMapper.toEntity(customer))
        );
    }

    public Customer findCustomerById(@NonNull long id) {
        return customerMapper.toDto(
            customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(format("resource with id: %s not found.", id)))
        );
    }

    public Customer updateCustomerById(Customer customer) {
        return customerMapper.toDto(
            customerRepository.save(customerMapper.toEntity(customer))
        );
    }

    public void deleteCustomerById(@NonNull long id) {
        customerRepository.deleteById(id);
    }

}
