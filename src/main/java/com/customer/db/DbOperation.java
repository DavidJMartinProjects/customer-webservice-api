package com.customer.db;

import java.util.List;

/**
 * The interface Db operation.
 *
 * @param <T> the record entity type
 *
 * @author davidjmartin
 */
public interface DbOperation<T> {

    /**
     * Save an record.
     *
     * @param entity the record
     *
     * @return the record
     */
    T save(T entity);

    /**
     * Find record by id t.
     *
     * @param id of the record
     *
     * @return the record
     */
    T findById(long id);

    /**
     * Find all records.
     *
     * @return the list of records
     */
    List<T> findAll();

    /**
     * Update record.
     *
     * @param entity the record
     *
     * @return the record
     */
    T update(T entity);

    /**
     * Delete record by id.
     *
     * @param id the record id
     */
    void deleteById(long id);

    /**
     * checks if an email is already registered registered.
     *
     * @param email the email
     *
     * @return the boolean
     */
    boolean isEmailRegistered(String email);

}