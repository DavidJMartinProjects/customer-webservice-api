package com.customer.exceptions;

import org.springframework.lang.NonNull;

/**
 * @author davidjmartin
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(@NonNull Long id) {
        super("Entity with id = " + id + " cannot be found in the database.");
    }

}