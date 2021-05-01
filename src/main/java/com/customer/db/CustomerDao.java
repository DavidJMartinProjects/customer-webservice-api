package com.customer.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.EntityNotFoundException;
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

    public CustomerEntity findCustomerById(@NonNull long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public CustomerEntity updateCustomerById(CustomerEntity customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(@NonNull long id) {
        customerRepository.deleteById(id);
    }

}
