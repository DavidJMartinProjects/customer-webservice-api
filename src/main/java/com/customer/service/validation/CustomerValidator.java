package com.customer.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.exceptions.ValidationFailureException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class CustomerValidator {

    @Autowired
    private DbOperation<Customer> dbOperation;

    public void validate(Customer customer) {
        log.info("validating request.");
        checkIfEmailIsRegistered(customer.getEmail());
    }

    private void checkIfEmailIsRegistered(String email) {
        if(dbOperation.isEmailRegistered(email)) {
            log.info("FAILED: email address is already registered.");
            throw new ValidationFailureException("email address '" + email + "' is already registered.");
        }
        log.info("PASSED: email address is unique.");
    }

}
