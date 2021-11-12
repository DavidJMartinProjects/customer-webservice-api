package com.customer.integration.factory;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.customer.db.DbOperation;
import com.customer.db.dao.model.entity.CustomerEntity;
import com.customer.db.dao.model.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DavidJMartin
 */
@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private DbOperation<Customer, CustomerPage> dbOperation;

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

            customers.add(customerEntity);
        }

        log.debug("Built {}.", customers);
        return customers.stream()
            .map(e -> mapper.toDto(e))
            .collect(Collectors.toList());
    }

    public Customer buildCustomer() {
        return buildTestCustomers(1)
            .stream()
            .findFirst()
            .orElse(new Customer());
    }

}
