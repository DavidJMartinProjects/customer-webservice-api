package com.customer.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NonNull;

import org.modelmapper.ModelMapper;
import com.app.openapi.model.Customer;

import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.EntityNotFoundException;

/**
 * @author davidjmartin
 */
@Component
public class CustomerDao {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerEntity
                    -> new ModelMapper().map(customerEntity, Customer.class))
            .collect(Collectors.toList());
    }


    public Customer findCustomerById(@NonNull long id) {
        CustomerEntity customerEntity =
            customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return new ModelMapper().map(customerEntity, Customer.class);
    }

    public void deleteCustomerById(@NonNull long id) {
        customerRepository.deleteById(id);
    }

}
