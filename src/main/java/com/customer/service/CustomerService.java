package com.customer.service;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;

import java.util.List;

/**
 * @author davidjmartin
 */
public interface CustomerService {
    List<Customer> getCustomers();
    CustomerPage getCustomers(int page, int size);
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomerById(Customer customer);
    void deleteCustomerById(long id);
}
