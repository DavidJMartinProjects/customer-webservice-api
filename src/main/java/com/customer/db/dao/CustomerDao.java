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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author davidjmartin
 */
@Component
@Slf4j
public class CustomerDao implements DbOperation<Customer, CustomerPage> {

    private static String CUSTOMER_ID_DOES_NOT_EXIST = "customer with id: %s does not exist.";

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        log.debug("fetching customers.");
        return customerRepository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public CustomerPage findAll(int page, int size) {
        log.debug("fetching customers. page {}, size {}.", page, size);
        Page<CustomerEntity> customerEntityPage =  customerRepository.findAll(PageRequest.of(page, size));

        return buildCustomerPage(customerEntityPage);
    }

    private CustomerPage buildCustomerPage(Page<CustomerEntity> customerEntityPage) {
        List<Customer> customers =  customerEntityPage.getContent()
            .stream()
            .map(mapper::toDto)
                .collect(Collectors.toList());

        CustomerPage customerPage = new CustomerPage();
        customerPage.setCustomers(customers);
        customerPage.setTotalPages((long) customerEntityPage.getTotalPages());
        customerPage.setTotalElements((long) customerEntityPage.getTotalElements());
        return customerPage;
    }

    @Override
    public Customer findById(long customerId) {
        log.debug("fetching customer with id: {}.", customerId);
        CustomerEntity entity = customerRepository.findById(customerId)
            .orElseThrow(() -> new CustomerServiceException(String.format(CUSTOMER_ID_DOES_NOT_EXIST, customerId)));
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
    public void deleteById(long customerId) {
        log.debug("deleting customer with id: {}.", customerId);
        customerRepository.deleteById(customerId);
    }

    @Override
    public void deleteAll() {
        log.debug("deleting all customers records.");
        customerRepository.deleteAll();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }

}