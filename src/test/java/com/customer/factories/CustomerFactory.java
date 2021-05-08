package com.customer.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper customerMapper;

    public void persistTestCustomers(int numOfCustomers) {
        customerDao.saveAll(buildTestCustomers(numOfCustomers));
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
        return buildTestCustomers(numOfCustomers)
            .stream()
            .map(customerEntity -> customerMapper.toDto(customerEntity))
            .collect(Collectors.toList());
    }

    public Customer buildUniqueCustomer() {
        final Customer customer = customerMapper.toDto(buildTestCustomers(1).get(0));
        customer.setEmail("unique@email.com");
        return customer;
    }

    public Customer findCustomerById(int customerId) {
        return customerDao.findCustomerById(customerId);
    }

}
