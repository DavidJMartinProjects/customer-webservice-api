package com.customer.integrationTests;

import static com.customer.controller.CustomerController.CUSTOMERS_BASE_PATH;

import java.util.List;

import com.app.openapi.model.Customer;
import com.customer.IntegrationTest;
import com.customer.db.entity.CustomerEntity;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class CreateCustomerIntegrationTests extends IntegrationTest {

    // <-- POST Requests Validation Tests -->
    @Test
    void GIVEN_emptyFirstName_WHEN_postRequestToCustomers_THEN_validationFailure() {
        // given
        final List<CustomerEntity> customerEntities = customerFactory.buildTestCustomers(1);
        final Customer customer = customerMapper.toDto(customerEntities.get(0));
        customer.setFirstName("");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("POST request to : /customers"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("request validation failure."))
                .jsonPath("$.message").value(Matchers.equalTo("[please provide a firstName]"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_emptyLastName_WHEN_postRequestToCustomers_THEN_validationFailure() {
        // given
        final List<CustomerEntity> customerEntities = customerFactory.buildTestCustomers(1);
        final Customer customer = customerMapper.toDto(customerEntities.get(0));
        customer.setLastName("");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("POST request to : /customers"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("request validation failure."))
                .jsonPath("$.message").value(Matchers.equalTo("[please provide a lastName]"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_emptyEmail_WHEN_postRequestToCustomers_THEN_validationFailure() {
        // given
        final List<CustomerEntity> customerEntities = customerFactory.buildTestCustomers(1);
        final Customer customer = customerMapper.toDto(customerEntities.get(0));
        customer.setEmail("");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("POST request to : /customers"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("request validation failure."))
                .jsonPath("$.message").value(Matchers.equalTo("[please provide an email]"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_multipleValidationViolations_WHEN_postRequestToCustomers_THEN_validationFailures() {
        // given
        final List<CustomerEntity> customerEntities = customerFactory.buildTestCustomers(1);
        final Customer customer = customerMapper.toDto(customerEntities.get(0));
        customer.setFirstName("");
        customer.setLastName("");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .jsonPath("$.url").value(Matchers.equalTo("POST request to : /customers"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("request validation failure."))
                .jsonPath("$.message").value(Matchers.containsString("please provide a firstName"))
                .jsonPath("$.message").value(Matchers.containsString("please provide a lastName"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void WHEN_postRequestToCustomersWithEmptyBody_THEN_badRequest() {
        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.empty(), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isBadRequest()
            .expectBody()
                .isEmpty();
    }

    // <-- Valid POST Request -->
    @Test
    void GIVEN_validCustomer_WHEN_postRequestToCustomers_THEN_created() {
        // given
        final List<CustomerEntity> customerEntities = customerFactory.buildTestCustomers(1);
        final Customer validCustomer = customerMapper.toDto(customerEntities.get(0));

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(validCustomer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isCreated()
            .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.firstName").value(Matchers.equalTo("test-firstName-1"))
                .jsonPath("$.lastName").value(Matchers.equalTo("test-lastName-1"))
                .jsonPath("$.address").value(Matchers.equalTo("test-address-1"))
                .jsonPath("$.country").value(Matchers.equalTo("test-country-1"))
                .jsonPath("$.email").value(Matchers.equalTo("test-email-1"));
    }

}
