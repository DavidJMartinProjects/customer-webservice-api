package com.customer.validation.validators;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.customer.db.DbOperation;
import com.customer.exception.exceptions.RequestValidationException;
import com.customer.validation.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class EmailRequestValidator implements RequestValidator {

    @Autowired
    private DbOperation<Customer, CustomerPage> dbOperation;

    @Override
    public void validate(Customer customer) {
        checkIfEmailIsRegistered(customer.getEmail());
        log.info("Email validated.");
    }

    private void checkIfEmailIsRegistered(String email) {
        if(dbOperation.isEmailRegistered(email)) {
            log.info("Validation failed: email address is already registered.");
            throw new RequestValidationException("Provided email address: '" + email + "' is already registered.");
        }
    }

}
