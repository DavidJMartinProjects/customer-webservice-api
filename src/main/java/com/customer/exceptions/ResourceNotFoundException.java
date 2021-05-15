package com.customer.exceptions;

/**
 * @author davidjmartin
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}