package com.customer.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.customer.model.entity.CustomerEntity;

/**
 * @author davidjmartin
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CrudRepository<CustomerEntity, Long> {
}
