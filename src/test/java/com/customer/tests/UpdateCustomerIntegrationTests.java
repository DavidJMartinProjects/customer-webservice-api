package com.customer.tests;

import static com.customer.controller.CustomerController.CUSTOMERS_BASE_PATH;

import com.app.openapi.generated.model.Customer;
import com.customer.IntegrationTest;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class UpdateCustomerIntegrationTests extends IntegrationTest {

    // <-- PUT Requests Integration Tests-->
    @Test
    void GIVEN_updatedCustomer_WHEN_putRequestToCustomerById_THEN_ok() {
        // given
        final Customer customer = customerDao.findCustomerById(CUSTOMER_ID_ONE);
        customer.setFirstName("Clint");
        customer.setLastName("Eastwood");

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_BASE_PATH + "/" + customer.getId())
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    void GIVEN_emptyRequestBody_WHEN_putRequestToCustomer_THEN_badRequest() {
        // given
        final long customerId = 1;

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_BASE_PATH + "/" + customerId)
            .body(Mono.empty(), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .isEmpty();
    }

}
