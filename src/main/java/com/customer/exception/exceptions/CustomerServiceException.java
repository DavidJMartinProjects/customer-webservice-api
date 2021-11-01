package com.customer.exception.exceptions;

/**
 * @author davidjmartin
 */
public class CustomerServiceException extends RuntimeException {
    public CustomerServiceException(String message) {
        super(message);
    }
}