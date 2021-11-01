package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.app.openapi.generated.api.CustomersApi;
import com.app.openapi.generated.model.Customer;
import com.customer.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@RestController
@Slf4j
public class CustomerController implements CustomersApi {

    static final String CUSTOMERS_API_BASE_PATH = "/customers";

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Override
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("received GET request to path: {}.", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.ok(customerServiceImpl.getCustomers());
    }

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        log.info("received POST request to path: {}.", CUSTOMERS_API_BASE_PATH);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServiceImpl.saveCustomer(customer));
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        log.info("received GET request to path: {}.", CUSTOMERS_API_BASE_PATH + id);
        return ResponseEntity.ok(customerServiceImpl.findCustomerById(id));
    }

    @Override
    public ResponseEntity<Customer> updateCustomerById(Integer id,Customer customer) {
        log.info("received PUT request to path: {}.", CUSTOMERS_API_BASE_PATH + id);
        customer.setId(id);
        return ResponseEntity.ok(customerServiceImpl.updateCustomerById(customer));
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(Integer id) {
        log.info("received DELETE request to path: {}.", CUSTOMERS_API_BASE_PATH +"/"+ id);
        customerServiceImpl.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
