package com.customer.db.dao;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.customer.db.DbOperation;
import com.customer.db.dao.repository.CustomerRepository;
import com.customer.exception.exceptions.CustomerServiceException;
import com.customer.model.entity.CustomerEntity;
import com.customer.model.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class CustomerDao implements DbOperation<Customer, CustomerPage> {

    private static final String DEFAULT_SORT_FIELD = "id";
    private static final String CUSTOMER_ID_DOES_NOT_EXIST = "customer with id: %s does not exist.";

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerPage findAll(int pageNumber, int pageSize, String sortKey, String sortDirection) {
        log.debug("fetching customers. page {}, size {}.", pageNumber, pageSize);
        Sort.Direction direction = "asc".equalsIgnoreCase(sortDirection) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortKey);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<CustomerEntity> customerEntityPage =  customerRepository.findAll(pageable);
        return buildCustomerPage(customerEntityPage);
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
            .orElseThrow(() -> new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, id)));
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