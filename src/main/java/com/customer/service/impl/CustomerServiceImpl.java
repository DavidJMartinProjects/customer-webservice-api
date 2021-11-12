package com.customer.service.impl;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.customer.db.DbOperation;
import com.app.openapi.generated.model.PageParams;
import com.customer.service.CustomerService;
import com.customer.validation.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author davidjmartin
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private DbOperation<Customer, CustomerPage> dbOperation;

    @Autowired
    private RequestValidator emailValidator;

    @Override
    public CustomerPage getCustomers(PageParams pageParams) {
        return dbOperation.findAll(pageParams);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("saving customer with id: {}.", customer.getId());
        emailValidator.validate(customer);
        return dbOperation.save(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        log.info("fetching customer with id: {}.", id);
        return dbOperation.findById(id);
    }

    @Override
    public Customer updateCustomerById(Customer customer) {
        log.info("updating customer with id: {}.", customer.getId());
        return dbOperation.update(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        log.info("deleting customer with id: {}.", id);
        dbOperation.deleteById(id);
    }

}
