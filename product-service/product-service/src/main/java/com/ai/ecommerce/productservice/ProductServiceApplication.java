package com.ai.ecommerce.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
        logger.info("Product Service Started Successfully!");
    }
}
