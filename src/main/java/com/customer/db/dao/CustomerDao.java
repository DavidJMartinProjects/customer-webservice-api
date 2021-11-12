package com.customer.db.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.app.openapi.generated.model.PageParams;
import com.customer.db.DbOperation;
import com.customer.db.dao.model.entity.CustomerEntity;
import com.customer.db.dao.model.mapper.CustomerMapper;
import com.customer.db.dao.query.CustomerSpecification;
import com.customer.db.dao.query.SearchCriteria;
import com.customer.db.dao.query.SearchOperation;
import com.customer.db.dao.repository.CustomerRepository;
import com.customer.exception.exceptions.CustomerServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class CustomerDao implements DbOperation<Customer, CustomerPage> {

    public static final int SEARCH_KEY_INDEX = 1;
    public static final int SEARCH_VALUE_INDEX = 3;

    public static final String KEY_VALUE_PAIR_REGEX = "(\\w+?)(:)(\\w+?),";

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerPage findAll(String searchCriteria, PageParams pageParams) {
        log.debug("pagination params: {}, searchCriteria: {}", pageParams, searchCriteria);

        Sort.Direction direction = "asc".equalsIgnoreCase(pageParams.getSortDirection()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, pageParams.getSortKey());
        Pageable pageable = PageRequest.of(pageParams.getPageNumber(), pageParams.getPageSize(), sort);

        Page<CustomerEntity> customerEntityPage;
        if(StringUtils.isEmpty(searchCriteria)) {
            customerEntityPage = customerRepository.findAll(pageable);
        } else {
            customerEntityPage = customerRepository.findAll(buildSpecification(searchCriteria), pageable);
        }
        return buildCustomerPage(customerEntityPage);
    }

    private CustomerSpecification buildSpecification(String search) {
        CustomerSpecification specification = new CustomerSpecification();
        final Pattern pattern = Pattern.compile(KEY_VALUE_PAIR_REGEX);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            specification.add(
                SearchCriteria.builder()
                    .key(matcher.group(SEARCH_KEY_INDEX))
                    .value(matcher.group(SEARCH_VALUE_INDEX))
                    .operation(SearchOperation.LIKE)
                    .build()
            );
        }
        return specification;
    }

    private CustomerPage buildCustomerPage(Page<CustomerEntity> customerEntityPage) {
        List<Customer> customers =  customerEntityPage.getContent()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());

        return new CustomerPage()
            .customers(customers)
            .totalPages((long) customerEntityPage.getTotalPages())
            .totalElements(customerEntityPage.getTotalElements());
    }

    @Override
    public Customer findById(long id) {
        log.debug("fetching customer with id: {}.", id);
        CustomerEntity entity = customerRepository.findById(id)
            .orElseThrow(() ->
                new CustomerServiceException(String.format("customer with id: %s does not exist.", id)));
        return mapper.toDto(entity);
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("saving customer with lastName: {}.", customer.getLastName());
        return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
    }

    @Override
    public Customer update(Customer customer) {
        log.debug("updating customer with id: {}.", customer.getId());
        return mapper.toDto(customerRepository.save(mapper.toEntity(customer)));
    }

    @Override
    public void deleteById(long id) {
        log.debug("deleting customer with id: {}.", id);
        customerRepository.deleteById(id);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}