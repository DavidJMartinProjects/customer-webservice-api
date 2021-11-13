package com.customer.filtering.model;

import com.app.openapi.generated.model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author DavidJMartin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerWrapper extends Customer {
}

