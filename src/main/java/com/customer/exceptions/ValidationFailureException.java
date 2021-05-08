package com.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author davidjmartin
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ValidationFailureException extends RuntimeException {

    public ValidationFailureException(String message) {
        super(message);
    }

}
