package com.customer.db;

import com.app.openapi.generated.model.PageParams;

/**
 * details supported db operations.
 *
 * @param <T> the record entity type
 *
 * @author davidjmartin
 */
public interface DbOperation<T, U> {

    /**
     * Find all records (paginated).
     *
     * @param searchCriteria String containing optional searchCriteria details
     *
     * @param pageParams Wrapper class specifying pagination parameters
     *
     * @return the page of records
     */
    U findAll(String searchCriteria, PageParams pageParams);

    /**
     * Find a record by id.
     *
     * @param id of the record
     *
     * @return the found record
     */
    T findById(long id);

    /**
     * Save a record.
     *
     * @param entity the record
     *
     * @return the saved record
     */
    T save(T entity);

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