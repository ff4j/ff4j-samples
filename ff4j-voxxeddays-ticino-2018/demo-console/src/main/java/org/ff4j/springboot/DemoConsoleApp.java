package org.ff4j.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "org.ff4j.springboot")
public class DemoConsoleApp {
    
    public static void main(String[] args) {
        SpringApplication.run(DemoConsoleApp.class, args);
    }
}

