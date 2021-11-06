package com.customer.service;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;

/**
 * @author davidjmartin
 */
public interface CustomerService {
    CustomerPage getCustomers(int pageNumber, int pageSize, String sortKey, String sortDirection);
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomerById(Customer customer);
    void deleteCustomerById(long id);
}
