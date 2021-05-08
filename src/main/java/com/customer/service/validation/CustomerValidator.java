package com.customer.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.repository.CustomerRepository;
import com.customer.exceptions.ValidationFailureException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class CustomerValidator {

    @Autowired
    private CustomerRepository customerRepository;

    public void validate(Customer customer) {
        log.info("validating request...");
        checkIfEmailIsUnique(customer.getEmail());
    }

    private void checkIfEmailIsUnique(String email) {
        if(customerRepository.existsByEmail(email)) {
            log.info("failure. email address is already registered.");
            throw new ValidationFailureException("email address '" + email + "' is already registered.");
        }
        log.info("success: email address is unique.");
    }

}
