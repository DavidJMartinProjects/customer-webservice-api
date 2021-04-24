package com.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.customer.db.CustomerDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DemoApplication.class
)
public abstract class TestsBase {

    @Autowired
    protected WebTestClient webTestClient;

    @Autowired
    protected CustomerDao customerDao;

}