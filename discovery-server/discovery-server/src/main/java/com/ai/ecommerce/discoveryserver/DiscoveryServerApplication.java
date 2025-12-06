package com.ai.ecommerce.discoveryserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(DiscoveryServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);

        logger.info("Discovery Server Started Successfully!");
    }
}
