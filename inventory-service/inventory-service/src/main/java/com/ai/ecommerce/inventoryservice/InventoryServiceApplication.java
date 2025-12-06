package com.ai.ecommerce.inventoryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient


public class InventoryServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
        logger.info("Inventory Service Started Successfully!");
    }
}
