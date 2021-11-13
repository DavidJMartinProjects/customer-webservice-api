package com.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.app.openapi.generated.api.CustomersApi;
import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.app.openapi.generated.model.PageParams;
import com.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@RestController
@Slf4j
public class CustomerController implements CustomersApi{

    static final String CUSTOMERS_API_BASE_PATH = "/customers";

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<CustomerPage> getCustomers(PageParams pageParams, String searchCriteria, String requiredFields) {
        log.info("GET request: {}", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.ok(customerService.getCustomers(pageParams, searchCriteria, requiredFields));
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        log.info("POST request: {}.", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        log.info("GET request: {}.", CUSTOMERS_API_BASE_PATH + "/" + id);
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

    @Override
    public ResponseEntity<Customer> updateCustomerById(Long id, Customer customer) {
        log.info("PUT request: {}.", CUSTOMERS_API_BASE_PATH + "/" + id);
        return ResponseEntity.ok(customerService.updateCustomerById(id, customer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(Long id) {
        log.info("DELETE request: {}.", CUSTOMERS_API_BASE_PATH + "/" + id);
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
