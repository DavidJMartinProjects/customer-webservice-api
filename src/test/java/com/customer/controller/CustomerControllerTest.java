package com.customer.controller;

import java.util.List;

import com.customer.TestsBase;
import com.customer.model.dto.Customer;
import org.junit.Test;

public class CustomerControllerTest extends TestsBase{

    public static final int CUSTOMER_ID_ONE = 1;

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

    @Test
    public void when_getRequestToCustomerById_then_ok() {

        final Customer expectedCustomer = customerDao.findCustomerById(CUSTOMER_ID_ONE);

        // when
        webTestClient
                .get()
                .uri("/customers/" + CUSTOMER_ID_ONE)

                // then
                .exchange()
                .expectStatus()
                .isOk()

                // and
                .expectBody(Customer.class)
                .isEqualTo(expectedCustomer);

    }

    @Test
    public void given_invalidId_when_getRequestToCustomerById_then_notFound() {

//        final long invalidId = 100;
//        final Customer expectedCustomer = customerDao.findCustomerById(invalidId);

        // when
//        webTestClient
//                .get()
//                .uri("/customers/" + invalidId)
//
//                // then
//                .exchange()
//                .expectStatus()
//                .isNotFound();
//
//                // and
//                .expectBody(Customer.class)
//                .isEqualTo(expectedCustomer);

    }

}