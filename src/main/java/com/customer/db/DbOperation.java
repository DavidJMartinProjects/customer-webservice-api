package com.customer.db;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface DbOperation<T> {

    /* CRUD */
    T save(T entity);
    T findById(long id);
    List<T> findAll();
    T update(T entity);
    void deleteById(long id);

    /* Queries */
    boolean isEmailRegistered(String email);

}