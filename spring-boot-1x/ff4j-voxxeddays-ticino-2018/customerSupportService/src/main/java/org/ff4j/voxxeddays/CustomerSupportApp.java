package org.ff4j.voxxeddays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Customer Services.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CustomerSupportApp {
    
    public static void main(String[] args) {
        SpringApplication.run(CustomerSupportApp.class, args);
    }
    
}