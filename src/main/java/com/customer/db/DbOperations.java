package com.customer.db;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface DbOperations<T> {

    // <-- CRUD -->
    T save(T customer);
    T findCustomerById(long id);
    List<T> findAllCustomers();
    T updateCustomerById(T customer);
    void deleteCustomerById(long id);

    // <-- Queries -->
    boolean isEmailRegistered(String email);

}