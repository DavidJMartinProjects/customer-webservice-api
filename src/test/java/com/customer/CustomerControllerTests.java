package com.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.openapi.model.Customer;
import com.customer.db.CustomerDao;
import com.customer.db.entity.CustomerEntity;
import com.customer.model.CustomerFactory;
import com.customer.service.mapper.CustomerMapper;
import com.customer.setup.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;


class CustomerControllerTests extends IntegrationTest {

    private static final int CUSTOMER_ID_ONE = 1;

    @Autowired
    private CustomerFactory customerFactory;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerMapper mapper;

    @BeforeEach
    public void init() {
        customerFactory.buildAndPersistTestData(3);
    }

    @Test
    void GIVEN_expectedCustomer_WHEN_getRequestToCustomerById_THEN_ok() {

        // given
        final Customer expectedCustomer = customerService.findCustomerById(CUSTOMER_ID_ONE);

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
    void GIVEN_expectedCustomers_WHEN_getRequestToCustomers_THEN_ok() {

        // given
        final List<Customer> expectedCustomers = customerFactory.getDefaultTestCustomers(3);

        // when
        webTestClient
            .get()
            .uri("/customers/")
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
            .uri("/customers/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
                .isNotFound()
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.message").value(Matchers.containsString(nonExistingId + " cannot be found"))
                .jsonPath("$.errorCode").value(Matchers.equalTo("NOT_FOUND"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_nonExistingId_WHEN_deleteRequestToCustomerById_THEN_notFound() {

        // given
        final long nonExistingId = 100;

        // when
        webTestClient
            .delete()
            .uri("/customers/" + nonExistingId)
            .exchange()

            // then
            .expectStatus()
                .isNotFound()
            .expectBody()
                .jsonPath("$.url").value(Matchers.containsString("/customers/" + nonExistingId))
                .jsonPath("$.message").value(Matchers.containsString("entity with id " + nonExistingId))
                .jsonPath("$.errorCode").value(Matchers.equalTo("NOT_FOUND"))
                .jsonPath("$.timestamp").isNotEmpty();
    }

    @Test
    void GIVEN_updatedCustomer_WHEN_putRequestToCustomerById_THEN_ok() {
        // given
        final CustomerEntity customerEntity = customerDao.findCustomerById(CUSTOMER_ID_ONE);
        customerEntity.setFirstName("Clint");
        customerEntity.setLastName("Eastwood");
        final Customer expectedCustomer = mapper.toCustomer(customerEntity);

        // when
        webTestClient
            .put()
            .uri("/customers/" + CUSTOMER_ID_ONE)
            .body(Mono.just(expectedCustomer), Customer.class)
            .exchange()

            // then
            .expectStatus()
                .isOk()
            .expectBody(Customer.class)
                .isEqualTo(expectedCustomer);

    }

    @Test
    void GIVEN_existingCustomerId_WHEN_deleteRequestToCustomerById_THEN_noContent() {
        // given
        final CustomerEntity customerEntity = customerDao.findCustomerById(CUSTOMER_ID_ONE);
        final Customer expectedCustomer = mapper.toCustomer(customerEntity);

        // when
        webTestClient
            .delete()
            .uri("/customers/" + CUSTOMER_ID_ONE)
            .exchange()

            // then
            .expectStatus()
                .isNoContent()
            .expectBody()
                .isEmpty();

    }

}
