package com.customer.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.db.dao.mapper.CustomerMapper;
import com.customer.db.dao.model.CustomerEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private DbOperation<Customer> dbOperation;

    public void saveTestCustomers(int numOfTestCustomers) {
        List<Customer> customers = buildTestCustomers(numOfTestCustomers);
        customers.forEach(dbOperation::save);
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
                    .image("test-imageUrl-" + index)
                    .build();

            log.info("saved customer record with id: {}.", customerEntity.getId());
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
        return dbOperation.findById(customerId);
    }

}
