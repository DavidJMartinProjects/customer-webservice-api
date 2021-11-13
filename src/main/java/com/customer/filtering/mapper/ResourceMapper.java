package com.customer.filtering.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.openapi.generated.model.Customer;
import com.customer.exception.exceptions.CustomerServiceException;
import com.customer.filtering.model.CustomerResource;
import com.customer.filtering.model.CustomerWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

/**
 * @author DavidJMartin
 */
@Component
@Slf4j
public class ResourceMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public CustomerResource toFilter(Customer customer) {
        log.debug("mapping customer entity with id: {} to dto.", customer.getId());
        return modelMapper.map(customer, CustomerResource.class);
    }

    public CustomerWrapper toWrapper(String jsonString) {
        log.debug("mapping json String: {} to customerWrapper.", jsonString);
        try {
            return objectMapper.readValue(jsonString, CustomerWrapper.class);
        } catch (JsonProcessingException e) {
            log.error("encountered error during mapping operation. {}", e.getMessage(), e);
            throw new CustomerServiceException("encountered error during mapping operation: "+ e.getMessage());
        }
    }

}
