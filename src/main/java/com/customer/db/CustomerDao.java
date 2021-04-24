package com.customer.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.EntityNotFoundException;
import com.customer.model.dto.Customer;
import com.customer.model.entity.CustomerEntity;
import lombok.NonNull;
import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Component
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll()
            .stream()
            .map(customerEntity -> new ModelMapper().map(customerEntity, Customer.class))
            .collect(Collectors.toList());
    }


    public Customer findCustomerById(@NonNull long id) {
        CustomerEntity customerEntity =
            customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        return new ModelMapper().map(customerEntity, Customer.class);
    }

}
