package com.customer.db;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface DbOperation<T> {

    /* CRUD */
    T save(T customer);
    T findById(long id);
    List<T> findAll();
    T updateById(T customer);
    void deleteById(long id);

    /* Queries */
    boolean isEmailRegistered(String email);

}