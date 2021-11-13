package com.customer.filtering.model;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

import com.app.openapi.generated.model.Customer;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author DavidJMartin
 */
@JsonFilter(CustomerResource.CUSTOMER_RESOURCE_FILTER)
@JsonInclude(Include.NON_NULL)
public class CustomerResource extends Customer {
    public static final String CUSTOMER_RESOURCE_FILTER = "CustomerResourceFilter";
}
