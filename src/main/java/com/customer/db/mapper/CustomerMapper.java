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

    public Customer toDto(CustomerEntity customerEntity) {
        log.info("mapping customer entity: {} to dto.", customerEntity);
        return modelMapper.map(customerEntity, Customer.class);
    }

    public CustomerEntity toEntity(Customer customer) {
        log.info("mapping customer dto: {} to entity.", customer);
        return modelMapper.map(customer, CustomerEntity.class);
    }

    public List<Customer> toDtos(List<CustomerEntity> customers) {
        log.info("mapping customer entities: {} to dtos.", customers);
        return customers.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

}
