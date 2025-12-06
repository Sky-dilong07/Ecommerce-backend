package com.ai.ecommerce.cartservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.ai.ecommerce")
@EnableDiscoveryClient
public class CartServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
        logger.info("Cart Service Started Successfully!");
    }
}
