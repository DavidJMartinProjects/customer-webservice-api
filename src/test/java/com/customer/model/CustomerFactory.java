package com.customer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import com.customer.service.CustomerService;
import com.customer.db.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    public void persistTestCustomers(int numOfCustomers) {
        List<CustomerEntity> entities = buildTestCustomers(numOfCustomers);
        customerRepository.saveAll(entities);
    }

    public List<CustomerEntity> buildTestCustomers(int numOfCustomers) {
        List<CustomerEntity> customers = new ArrayList<>();

        for(int index = 1; index <= numOfCustomers; index++) {
            CustomerEntity customerEntity =
                CustomerEntity.builder()
                    .id(index)
                    .firstName("test-firstName-" + index)
                    .lastName("test-lastName-" + index)
                    .address("test-address-" + index)
                    .city("test-city-" + index)
                    .country("test-country-" + index)
                    .email("test-email-" + index)
                    .build();
            customers.add(customerEntity);
        }
        log.debug("Build {}.", customers);
        return customers;
    }

    public List<Customer> getTestCustomers(int numOfCustomers) {
        List<CustomerEntity> customerEntities = buildTestCustomers(numOfCustomers);
        return customerEntities.stream()
            .map(customerEntity -> new ModelMapper().map(customerEntity, Customer.class))
            .collect(Collectors.toList());
    }

    public Customer findCustomerById(int customerId) {
        return customerService.findCustomerById(customerId);
    }

}
