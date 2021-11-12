package com.customer.integration.usecase;

import org.springframework.http.HttpStatus;

import com.app.openapi.generated.model.Customer;
import com.customer.integration.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

/**
 * @author DavidJMartin
 */
class CreateCustomerTests extends IntegrationTest {

    // <-- Negative POST Requests Integration Tests -->
    @Test
    void GIVEN_registeredEmail_WHEN_postRequestToCustomers_THEN_alreadyRegistered() {
        // given
        final Customer customer = customerFactory.buildCustomer();

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
            .isEqualTo(HttpStatus.CONFLICT)
            .expectBody()
            .jsonPath("$.url").value(Matchers.equalTo("POST request to : /customers"))
            .jsonPath("$.errorCode").value(Matchers.equalTo("request validation failure."))
            .jsonPath("$.message").value(Matchers.equalTo("Provided email address: 'dm@email.com' is already registered."))
            .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_emptyFirstName_WHEN_postRequestToCustomers_THEN_validationFailure() {
        // given
        final Customer customer = customerFactory.buildCustomer();
        customer.setFirstName("");
        customer.setEmail("unique@email.com");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
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
        final Customer customer = customerFactory.buildCustomer();
        customer.setLastName("");
        customer.setEmail("unique@email.com");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
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
        final Customer customer = customerFactory.buildCustomer();
        customer.setEmail("");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
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
    void GIVEN_emptyFirstNameAndLastName_WHEN_postRequestToCustomers_THEN_expectedValidationViolationMessages() {
        // given
        final Customer customer = customerFactory.buildCustomer();
        customer.setFirstName("");
        customer.setLastName("");
        customer.setEmail("unique@email.com");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
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
            .uri(CUSTOMERS_API_BASE_PATH)
            .body(Mono.empty(), Customer.class)
            .exchange()

            // then
            .expectStatus()
            .isBadRequest()
            .expectBody()
            .isEmpty();
    }

    // <-- Positive POST Requests Integration Tests -->
    @Test
    void GIVEN_validCustomer_WHEN_postRequestToCustomers_THEN_created() {
        // given
        final Customer customer = customerFactory.buildCustomer();
        customer.setEmail("unique@email.com");

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_API_BASE_PATH)
            .body(Mono.just(customer), Customer.class)
            .exchange()

            // then
            .expectStatus()
            .isCreated()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.firstName").value(Matchers.equalTo("David"))
            .jsonPath("$.lastName").value(Matchers.equalTo("Martin"))
            .jsonPath("$.address").value(Matchers.equalTo("Main St."))
            .jsonPath("$.country").value(Matchers.equalTo("Ireland"))
            .jsonPath("$.email").value(Matchers.equalTo("unique@email.com"));
    }

}