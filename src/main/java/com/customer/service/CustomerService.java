package com.customer.service;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.app.openapi.generated.model.PageParams;


/**
 * @author davidjmartin
 */
public interface CustomerService {
    CustomerPage getCustomers(PageParams pageParams, String searchCriteria);
    Customer saveCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomerById(Customer customer);
    void deleteCustomerById(long id);
}
