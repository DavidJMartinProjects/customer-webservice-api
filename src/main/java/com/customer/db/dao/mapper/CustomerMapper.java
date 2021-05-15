package com.customer.db.dao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.db.dao.model.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Slf4j
@Component
public class CustomerMapper {

    @Autowired
    private ModelMapper modelMapper;

    private CustomerMapper() {}

    public Customer toDto(CustomerEntity customerEntity) {
        log.debug("mapping customer entity with id: {} to dto.", customerEntity.getId());
        return modelMapper.map(customerEntity, Customer.class);
    }

    public CustomerEntity toEntity(Customer customer) {
        log.debug("mapping customer dto with to entity.");
        return modelMapper.map(customer, CustomerEntity.class);
    }

}
