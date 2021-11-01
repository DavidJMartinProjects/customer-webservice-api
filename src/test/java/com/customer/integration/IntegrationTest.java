package com.customer.integration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.factory.CustomerFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author DavidJMartin
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureWebTestClient
@Slf4j
public abstract class IntegrationTest {

    protected static final String CUSTOMERS_API_BASE_PATH = "/customers";

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerFactory customerFactory;

}

