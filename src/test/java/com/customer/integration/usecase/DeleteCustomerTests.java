package com.customer.integration.usecase;

import org.springframework.http.HttpStatus;

import com.app.openapi.generated.model.Customer;
import com.customer.integration.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author DavidJMartin
 */
class DeleteCustomerTests extends IntegrationTest {

    // <-- Positive DELETE Request Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final Customer customer = customerFactory.buildCustomer();

        // when
        webTestClient
            .delete()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + customer.getId())
            .exchange()

            // then
            .expectStatus()
            .isNoContent()
            .expectBody()
            .isEmpty();
    }

    // <-- Negative DELETE Request Integration Tests -->
    @Test
    void GIVEN_nonExistingId_WHEN_deleteRequestToCustomerById_THEN_serverError() {
        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .delete()
            .uri(CUSTOMERS_API_BASE_PATH + "/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
            .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
            .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
            .jsonPath("$.errorCode").value(Matchers.equalTo("encountered exception."))
            .jsonPath("$.timestamp").isNotEmpty();
    }

}
