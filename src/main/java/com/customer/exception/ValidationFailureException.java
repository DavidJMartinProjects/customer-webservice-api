package com.customer.exception;

/**
 * @author davidjmartin
 */
public class ValidationFailureException extends RuntimeException {
    public ValidationFailureException(String message) {
        super(message);
    }
}
