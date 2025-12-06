package com.ai.ecommerce.gatewayservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {

    private static final Logger logger = LoggerFactory.getLogger(GatewayServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
        logger.info("Gateway Service Started Successfully!");
    }
}
