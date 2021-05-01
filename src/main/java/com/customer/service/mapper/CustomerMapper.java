package com.customer.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Component
public class CustomerMapper {

    @Autowired
    private ModelMapper modelMapper;

    private CustomerMapper() {}

    public Customer toCustomer(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, Customer.class);
    }

    public CustomerEntity toCustomerEntity(Customer customer) {
        return modelMapper.map(customer, CustomerEntity.class);
    }

}
