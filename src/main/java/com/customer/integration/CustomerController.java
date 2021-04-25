package com.customer.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.dto.Customer;
import com.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@RestController
@RequestMapping(CustomerController.CUSTOMERS_BASE_PATH)
public class CustomerController {

    public static final String CUSTOMERS_BASE_PATH = "customers/";

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers() {
        log.info("received GET request to {}", CUSTOMERS_BASE_PATH);
        return customerService.getCustomers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") long id) {
        log.info("received GET request to {}{}.", CUSTOMERS_BASE_PATH, id);
        return customerService.findCustomerById(id);
    }

}
