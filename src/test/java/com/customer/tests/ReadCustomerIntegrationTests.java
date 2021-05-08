package com.customer.tests;


import static com.customer.controller.CustomerController.CUSTOMERS_BASE_PATH;

import java.util.List;

import com.app.openapi.model.Customer;
import com.customer.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

class ReadCustomerIntegrationTests extends IntegrationTest {

    // <-- GET Requests Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_getRequestToCustomerById_THEN_ok() {
        // given
        final Customer customer = customerFactory.findCustomerById(CUSTOMER_ID_ONE);

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_BASE_PATH + "/" + customer.getId())
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    void GIVEN_expectedCustomers_WHEN_getRequestToCustomers_THEN_ok() {
        // given
        final List<Customer> expectedCustomers = customerFactory.getTestCustomers(3);

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_BASE_PATH)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBodyList(Customer.class)
                .hasSize(3)
                .isEqualTo(expectedCustomers);
    }

    @Test
    void GIVEN_nonExistingId_WHEN_getRequestToCustomerById_THEN_notFound() {
        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_BASE_PATH + "/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
                .isNotFound()
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.message").value(Matchers.containsString(nonExistingId + " not found"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("NOT_FOUND"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}
