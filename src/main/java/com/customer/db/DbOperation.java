package com.customer.db;

import com.customer.model.page.PageParams;

/**
 * details supported db operations.
 *
 * @param <T> the record entity type
 *
 * @author davidjmartin
 */
public interface DbOperation<T, U> {

    /**
     * Save a record.
     *
     * @param entity the record
     *
     * @return the saved record
     */
    T save(T entity);

    /**
     * Find a record by id.
     *
     * @param id of the record
     *
     * @return the found record
     */
    T findById(long id);

    /**
     * Find all records (paginated).
     *
     * @param pageParams Wrapper class for pagination parameters
     *
     * @return the page of records
     */
    U findAll(PageParams pageParams);

    /**
     * Update a record.
     *
     * @param entity the record
     *
     * @return the updated record
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
     * @return true if email is registered
     */
    boolean isEmailRegistered(String email);
}