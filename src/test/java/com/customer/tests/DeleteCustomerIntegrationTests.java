package com.customer.tests;

import static com.customer.controller.CustomerController.CUSTOMERS_BASE_PATH;

import com.app.openapi.model.Customer;
import com.customer.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class DeleteCustomerIntegrationTests extends IntegrationTest {

    // <-- DELETE Requests Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final Customer customer = customerDao.findCustomerById(CUSTOMER_ID_ONE);

        // when
        webTestClient
            .delete()
            .uri(CUSTOMERS_BASE_PATH + "/" + customer.getId())
            .exchange()

            // then
            .expectStatus()
                .isNoContent()
            .expectBody()
                .isEmpty();
    }

    // ToDo: handle the JPA exception & return a more precise error message for this delete scenario
    @Test
    void GIVEN_nonExistingId_WHEN_deleteRequestToCustomerById_THEN_serverError() {
        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .delete()
            .uri(CUSTOMERS_BASE_PATH + "/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
                .is5xxServerError()
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.errorCode").value(Matchers.equalTo("INTERNAL_ERROR"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}
