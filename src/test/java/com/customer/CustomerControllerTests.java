package com.customer;

import static com.customer.controller.CustomerController.CUSTOMERS_BASE_PATH;

import java.util.Collections;
import java.util.List;

import com.app.openapi.model.Customer;
import com.customer.db.entity.CustomerEntity;
import com.customer.setup.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;


class CustomerControllerTests extends IntegrationTest {

    // <-- GET Requests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_getRequestToCustomerById_THEN_ok() {
        // given
        final Customer customer = customerFactory.findCustomerById(CUSTOMER_ID_ONE);

        // when
        webTestClient
            .get()
            .uri(CUSTOMERS_BASE_PATH + customer.getId())
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
            .uri(CUSTOMERS_BASE_PATH + nonExistingId)
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

    // <-- PUT Requests -->
    @Test
    void GIVEN_updatedCustomer_WHEN_putRequestToCustomerById_THEN_ok() {
        // given
        final CustomerEntity customerEntity = customerDao.findCustomerById(CUSTOMER_ID_ONE);
        customerEntity.setFirstName("Clint");
        customerEntity.setLastName("Eastwood");
        final Customer expectedCustomer = customerMapper.toDto(customerEntity);

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_BASE_PATH + customerEntity.getId())
            .body(Mono.just(expectedCustomer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Customer.class)
                .isEqualTo(expectedCustomer);
    }

    @Test
    void GIVEN_emptyRequestBody_WHEN_putRequestToCustomer_THEN_badRequest() {
        // given
        final long customerId = 1;

        // when
        webTestClient
            .put()
            .uri(CUSTOMERS_BASE_PATH + customerId)
            .body(Mono.empty(), Customer.class)
            .exchange()

            // then
            .expectStatus()
                 .isBadRequest()
            .expectBody()
                .isEmpty();
    }

    // <-- POST Requests -->
    @Test
    void GIVEN_newCustomer_WHEN_postRequestToCustomers_THEN_ok() {
        // given
        final Customer customer = customerFactory.buildTestCustomer();

        // when
        webTestClient
            .post()
            .uri(CUSTOMERS_BASE_PATH)
            .body(Mono.just(Collections.singletonList(customer)), List.class)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody()
                .jsonPath("$[0].id").isNotEmpty()
                .jsonPath("$[0].firstName").value(Matchers.equalTo("test-firstName"))
                .jsonPath("$[0].lastName").value(Matchers.equalTo("test-lastName"))
                .jsonPath("$[0].address").value(Matchers.equalTo("test-address"))
                .jsonPath("$[0].country").value(Matchers.equalTo("test-country"))
                .jsonPath("$[0].email").value(Matchers.equalTo("test@email.com"));
    }

    @Test
    void GIVEN_noRequestBody_WHEN_postRequestToCustomers_THEN_badRequest() {
        // given
        final Customer customer = customerFactory.buildTestCustomer();

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

    // <-- DELETE Requests -->
    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final CustomerEntity customerEntity = customerDao.findCustomerById(CUSTOMER_ID_ONE);

        // when
        webTestClient
            .delete()
            .uri(CUSTOMERS_BASE_PATH + customerEntity.getId())
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
            .uri(CUSTOMERS_BASE_PATH + nonExistingId)
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
