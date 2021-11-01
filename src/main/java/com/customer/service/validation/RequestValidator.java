package com.customer.service.validation;

import com.app.openapi.generated.model.Customer;

/**
 * @author David
 */
public interface RequestValidator {
    void validate(Customer customer);
}
