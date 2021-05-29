package com.customer.exception;

/**
 * @author davidjmartin
 */
public class CustomerServiceException extends RuntimeException {
    public CustomerServiceException(String message) {
        super(message);
    }
}