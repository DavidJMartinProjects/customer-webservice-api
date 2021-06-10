package com.customer.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.factory.CustomerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author DavidJMartin
 */
@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    protected static final String CUSTOMERS_API_BASE_PATH = "/customers";

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerFactory customerFactory;

    @Autowired
    protected DbOperation<Customer> dbOperation;

    @BeforeEach
    public void init() {
        customerFactory.saveTestCustomers(3);
    }

    @AfterEach
    public void teardown() {
        dbOperation.deleteAll();
    }

}

