package com.customer.db;

import org.springframework.data.domain.Page;

import java.util.List;

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
     * @param pageNumber the pageNumber number
     *
     * @param pageSize the number of records per pageNumber
     *
     * @param sortKey the number of records per pageNumber
     *
     * @param sortDirection the number of records per pageNumber
     *
     * @return the pageNumber of records
     */
    U findAll(int pageNumber, int pageSize, String sortKey, String sortDirection);

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