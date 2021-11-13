package com.customer.filtering;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.app.openapi.generated.model.CustomerPage;
import com.customer.exception.exceptions.CustomerServiceException;
import com.customer.filtering.mapper.ResourceMapper;
import com.customer.filtering.model.CustomerResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class FieldFilterUtil {

    @Autowired
    private ResourceMapper mapper;

    public CustomerPage filter(String fields, CustomerPage customerPage) {
        List<Customer> filteredCustomers =
            customerPage.getCustomers().stream()
                .map(e -> mapper.toFilter(e))
                .map(customerResource -> extractFields(fields, customerResource))
                .map(jsonString -> mapper.toWrapper(jsonString))
                .collect(Collectors.toList());

        return new CustomerPage()
            .customers(filteredCustomers)
            .totalElements(customerPage.getTotalElements())
            .totalPages(customerPage.getTotalPages());
    }

    private String extractFields(String fields, CustomerResource customerResource) {
        // setup jackson filter provider
        Set<String> requiredFields = new HashSet<>(Arrays.asList(fields.split(",")));
        FilterProvider filters =
            new SimpleFilterProvider().addFilter(CustomerResource.CUSTOMER_RESOURCE_FILTER,
                    SimpleBeanPropertyFilter.filterOutAllExcept(requiredFields));

        try {
            return new ObjectMapper().writer(filters)
                .withDefaultPrettyPrinter()
                .writeValueAsString(customerResource);
        } catch (JsonProcessingException exception) {
            log.error("encountered error filtering response fields: {}", exception.getMessage(), exception);
            throw new CustomerServiceException(exception.getMessage());
        }
    }

}
