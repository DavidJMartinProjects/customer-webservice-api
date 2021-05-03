package com.customer.db;

import static java.lang.String.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ResourceNotFoundException;
import lombok.NonNull;

/**
 * @author davidjmartin
 */


@Component
public class CustomerDao {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<CustomerEntity> saveAll(List<CustomerEntity> entities) {
        return customerRepository.saveAll(entities);
    }

    public CustomerEntity findCustomerById(@NonNull long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(format("resource with id: %s not found.", id)));
    }

    public CustomerEntity updateCustomerById(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(@NonNull long id) {
        customerRepository.deleteById(id);
    }

}
