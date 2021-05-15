package com.customer.exception;

/**
 * @author davidjmartin
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}