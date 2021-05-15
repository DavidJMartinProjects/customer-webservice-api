package com.customer.db.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
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

    public Customer mapToDto(CustomerEntity customerEntity) {
        log.info("mapping customer entity with id: {} to dto.", customerEntity.getId());
        return modelMapper.map(customerEntity, Customer.class);
    }

    public CustomerEntity mapToEntity(Customer customer) {
        log.info("mapping customer dto with to entity.");
        return modelMapper.map(customer, CustomerEntity.class);
    }

    public List<Customer> mapToDtos(List<CustomerEntity> customers) {
        log.info("mapping customer entities to dtos.");
        return customers.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

}
