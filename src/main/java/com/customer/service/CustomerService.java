package com.customer.service;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.app.openapi.generated.model.PageParams;


/**
 * @author davidjmartin
 */
public interface CustomerService {
    CustomerPage getCustomers(PageParams pageParams, String searchCriteria, String fields);
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomerById(long id, Customer customer);
    void deleteCustomerById(long id);

    // validation
    boolean isEmailRegistered(String email);
}
