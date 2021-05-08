package com.customer.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.customer.db.CustomerDao;
import com.customer.factories.CustomerFactory;
import com.customer.db.mapper.CustomerMapper;
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
    protected CustomerDao customerDao;

    @Autowired
    protected CustomerMapper customerMapper;

    @BeforeEach
    public void init() {
        customerFactory.persistTestCustomers(3);
    }

}

