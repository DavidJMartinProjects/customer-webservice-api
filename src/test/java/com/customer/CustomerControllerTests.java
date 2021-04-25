package com.customer;

import java.util.List;

import com.customer.base.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import com.app.openapi.model.Customer;

class CustomerControllerTests extends IntegrationTest {

    public static final int CUSTOMER_ID_ONE = 1;

    @Test
    void GIVEN_expectedCustomers_WHEN_getRequestToCustomers_THEN_ok() throws Exception {

        // given
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
    void GIVEN_expectedCustomer_WHEN_getRequestToCustomerById_THEN_ok() {

        // given
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
    void GIVEN_nonExistingId_WHEN_getRequestToCustomerById_THEN_notFound() {

        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .get()
            .uri("/customers/" + nonExistingId)

            // then
            .exchange()
            .expectStatus()
                .isNotFound()

            // and
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.message").value(Matchers.containsString(nonExistingId + " cannot be found"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("NOT_FOUND"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

}