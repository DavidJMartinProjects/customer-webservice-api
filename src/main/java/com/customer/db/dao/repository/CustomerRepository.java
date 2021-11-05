package com.customer.db.dao.repository;

import com.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author davidjmartin
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, PagingAndSortingRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);
}
