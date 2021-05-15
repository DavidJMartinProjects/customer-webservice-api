package com.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.app.openapi.generated.model.Customer;
import com.customer.db.DbOperation;
import com.customer.db.dao.mapper.CustomerMapper;
import com.customer.factories.CustomerFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;

@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class IntegrationTest {

    protected static final int CUSTOMER_ID_ONE = 1;

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerFactory customerFactory;

    @Autowired
    protected DbOperation<Customer> dbOperation;

    @BeforeEach
    public void init() {
        customerFactory.persistTestCustomers(3);
    }

}

