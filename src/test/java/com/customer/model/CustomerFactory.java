package com.customer.model;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import com.customer.db.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomerFactory {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerRepository customerRepository;

    public void wipeTestData() {
        customerRepository.deleteAll();
        customerRepository.flush();
    }

    public List<Customer> getDefaultTestCustomers(int numOfCustomers) {
        List<CustomerEntity> customerEntities = buildTestCustomerEntities(numOfCustomers);

        return customerEntities.stream()
            .map(customerEntity
                    -> new ModelMapper().map(customerEntity, Customer.class))
            .collect(Collectors.toList());
}

    public void buildAndPersistTestData(int numOfCustomers) {
        List<CustomerEntity> entities = buildTestCustomerEntities(3);
        customerRepository.saveAll(entities);
        customerRepository.flush();
    }

    private List<CustomerEntity> buildTestCustomerEntities(int numOfCustomers) {
        List<CustomerEntity> customers = new ArrayList<>();

        for(int index = 1; index <= numOfCustomers; index++) {
            CustomerEntity customerEntity =
                CustomerEntity.builder()
                    .id(index)
                    .firstName("test-firstName-" + (index))
                    .lastName("test-lastName-" + (index))
                    .address("test-address-" + (index))
                    .city("test-city-" + (index))
                    .country("test-country-" + (index))
                    .email("test-email-" + (index))
                    .build();
            customers.add(customerEntity);
        }
        log.debug("Build {}.", customers);
        return customers;
    }

}
