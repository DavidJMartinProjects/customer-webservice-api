package com.customer.db;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface CrudOperations<T> {

    List<T> findAllCustomers();
    T findCustomerById(long id);
    T save(T customer);
    T updateCustomerById(T customer);
    void deleteCustomerById(long id);
    boolean isEmailAlreadyRegistered(String email);

}