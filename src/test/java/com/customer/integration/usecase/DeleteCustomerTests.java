package com.customer.integration.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.app.openapi.generated.model.Customer;
import com.customer.db.dao.CustomerDao;
import com.customer.integration.IntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * @author DavidJMartin
 */
public class DeleteCustomerTests extends IntegrationTest {

    // <-- Positive DELETE Request Integration Tests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final Customer customer = dbOperation.findAll().get(0);

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
            .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
            .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
            .jsonPath("$.errorCode").value(Matchers.equalTo("resource not found."))
            .jsonPath("$.timestamp").isNotEmpty();
    }

}
