package com.customer.setup;

import com.customer.db.CustomerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
<<<<<<< HEAD:src/test/java/com/customer/base/IntegrationTest.java
import org.springframework.transaction.annotation.Transactional;

import com.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
=======
>>>>>>> a04613af4b933e0e4d1fe2204d597b72080d586f:src/test/java/com/customer/setup/IntegrationTest.java

@Slf4j
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerService customerService;

}

