package com.customer.controller;

import java.util.List;

import com.customer.TestsBase;
import com.customer.model.dto.Customer;
import org.junit.Test;

public class CustomerControllerTest extends TestsBase {

    @Test
    public void when_getRequestToCustomers_then_ok() throws Exception {

        final List<Customer> expectedCustomers = customerDao.findAllCustomers();

        // when
        webTestClient
            .get()
                .uri("/customers/")

            // then
            .exchange()
                .expectStatus()
                    .isOk()

            // and
            .expectBodyList(Customer.class)
                .hasSize(3)
                .isEqualTo(expectedCustomers);

    }

}