package com.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.app.openapi.generated.model.PageParams;
import com.customer.db.DbOperation;
import com.customer.filtering.FieldFilterUtil;
import com.customer.service.CustomerService;
import com.customer.validation.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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

    @Autowired
    private FieldFilterUtil fieldFilterUtil;

    @Override
    public CustomerPage getCustomers(PageParams pageParams, String searchCriteria, String fields) {
        if(StringUtils.isEmpty(fields)) {
            return dbOperation.findAll(pageParams, searchCriteria);
        }
        return fieldFilterUtil.filter(fields, dbOperation.findAll(pageParams, searchCriteria));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        log.debug("saving customer with id: {}.", customer.getId());
        emailValidator.validate(customer);
        return dbOperation.save(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        log.debug("fetching customer with id: {}.", id);
        return dbOperation.findById(id);
    }

    @Override
    public Customer updateCustomerById(long id, Customer customer) {
        log.debug("updating customer with id: {}.", customer.getId());
        customer.setId(id);
        return dbOperation.update(customer);
    }

    @Override
    public void deleteCustomerById(long id) {
        log.debug("deleting customer with id: {}.", id);
        dbOperation.deleteById(id);
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return dbOperation.isEmailRegistered(email);
    }

}
