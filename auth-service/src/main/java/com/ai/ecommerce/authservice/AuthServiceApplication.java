package com.ai.ecommerce.authservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
        logger.info("Auth Service Started Successfully!");
    }
}
