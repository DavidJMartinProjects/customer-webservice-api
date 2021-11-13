package com.customer.db.dao.specification.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.customer.db.dao.model.entity.CustomerEntity;
import com.customer.db.dao.specification.enums.SearchOperation;

/**
 * @author DavidJMartin
 */
public class CustomerSpecification implements Specification<CustomerEntity> {

    private List<SearchCriteria> searchCriteria;

    public CustomerSpecification() {
        this.searchCriteria = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteria.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<CustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        // add search criteria to predicates
        for (SearchCriteria criteria : searchCriteria) {
            if (criteria.getOperation().equals(SearchOperation.LIKE)) {
                predicates.add(builder.like(
                    builder.lower(root.get(criteria.getKey())),
                    "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.STARTS_WITH)) {
                predicates.add(builder.like(
                    builder.lower(root.get(criteria.getKey())),
                    "%" + criteria.getValue().toString().toLowerCase()));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}