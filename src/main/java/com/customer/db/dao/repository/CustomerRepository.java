package com.customer.db.dao.repository;

import com.customer.db.dao.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author davidjmartin
 */
@Repository
public interface CustomerRepository extends
    JpaRepository<CustomerEntity, Long>,
    PagingAndSortingRepository<CustomerEntity, Long>,
    JpaSpecificationExecutor<CustomerEntity> {
    boolean existsByEmail(String email);
}
