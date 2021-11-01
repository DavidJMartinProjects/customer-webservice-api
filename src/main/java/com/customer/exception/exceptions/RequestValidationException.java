package com.customer.exception.exceptions;

/**
 * @author davidjmartin
 */
public class RequestValidationException extends RuntimeException {
    public RequestValidationException(String message) {
        super(message);
    }
}
