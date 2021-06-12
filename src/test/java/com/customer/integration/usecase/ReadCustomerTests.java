package com.customer.integration.usecase;


import java.util.List;

import org.springframework.http.HttpStatus;

import com.app.openapi.generated.model.Customer;
import com.customer.integration.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author DavidJMartin
 */
class ReadCustomerTests extends IntegrationTest {

    // <-- Positive GET Requests Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_getRequestToCustomerById_THEN_ok() {
        // given
        final Customer customer = dbOperation.findAll().get(0);

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + customer.getId())
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
        final List<Customer> expectedCustomers = customerFactory.buildTestCustomers(3);

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_API_BASE_PATH)
            .exchange()

            // then
            .expectStatus()
            .isOk()
            .expectBodyList(Customer.class)
            .hasSize(3);

    }

    // <-- Negative GET Requests Integration Tests -->
    @Test
    void GIVEN_nonExistingId_WHEN_getRequestToCustomerById_THEN_notFound() {
        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
            .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
            .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
            .jsonPath("$.message").value(Matchers.containsString("customer with id: " + nonExistingId + " does not exist."))
            .jsonPath("$.errorCode").value(Matchers.equalTo("resource not found."))
            .jsonPath("$.timestamp").isNotEmpty();
    }

}