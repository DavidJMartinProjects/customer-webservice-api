package com.customer.tests;

import com.app.openapi.generated.model.Customer;
import com.customer.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author DavidJMartin
 */
public class DeleteCustomerIntegrationTests extends IntegrationTest {

    // <-- Positive DELETE Request Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final Customer customer = customerFactory.findCustomerById(CUSTOMER_ID_ONE);

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

    // ToDo: handle the JPA exception & return a more precise error message for this delete scenario
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
                .is5xxServerError()
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.errorCode").value(Matchers.equalTo("internal server error."))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}
