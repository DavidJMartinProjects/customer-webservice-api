package com.customer.db;

import java.util.List;

/**
 * DbOperation interface: details supported db operations.
 *
 * @param <T> the record entity type
 *
 * @author davidjmartin
 */
public interface DbOperation<T> {

    /**
     * Save a record.
     *
     * @param entity the record
     *
     * @return the record
     */
    T save(T entity);

    /**
     * Find a record by id.
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
     * Update a record.
     *
     * @param entity the record
     *
     * @return the record
     */
    T update(T entity);

    /**
     * Delete a record by id.
     *
     * @param id the record id
     */
    void deleteById(long id);

    /**
     * returns true if the provided email is already registered.
     *
     * @param email the email
     *
     * @return the boolean
     */
    boolean isEmailRegistered(String email);

}