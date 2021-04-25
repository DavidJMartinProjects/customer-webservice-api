package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.openapi.api.CustomersApi;
import com.app.openapi.model.Customer;
import com.customer.service.CustomerService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


/**
 * @author davidjmartin
 */
@Slf4j
@RestController
@RequestMapping("/customers/")
public class CustomerController implements CustomersApi {

    @Autowired
    private CustomerService customerService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers() {
        log.info("received GET request to {}", "/customers/");
        return ResponseEntity.ok(customerService.getCustomers());
    }

    @Override
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        log.info("received GET request to {}{}.", "/customers/", id);
        return ResponseEntity.ok(customerService.findCustomerById(id));
    }

}
