package com.customer.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.db.dao.CustomerDao;
import com.customer.db.model.CustomerEntity;
import com.customer.db.dao.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper mapper;

    public void persistTestCustomers(int numOfCustomers) {
        for(Customer customerEntity : buildTestCustomers(numOfCustomers)) {
            customerDao.save(customerEntity);
        }
    }

    public List<Customer> buildTestCustomers(int numOfCustomers) {
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
        return customers.stream()
            .map(e -> mapper.toDto(e))
            .collect(Collectors.toList());
    }

    public Customer buildUniqueCustomer() {
        final Customer customer = buildTestCustomers(1).get(0);
        customer.setEmail("unique@email.com");
        return customer;
    }

    public Customer findCustomerById(int customerId) {
        return customerDao.findCustomerById(customerId);
    }

}
