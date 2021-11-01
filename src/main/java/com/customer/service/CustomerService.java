package com.customer.service;

import java.util.List;

import com.app.openapi.generated.model.Customer;

/**
 * @author davidjmartin
 */
public interface CustomerService {
    List<Customer> getCustomers();
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomerById(Customer customer);
    void deleteCustomerById(long id);
}
