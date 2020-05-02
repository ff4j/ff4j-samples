package org.ff4j.sample.dynamodb;

import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ff4jSampleDynamodbApplication {

    @Autowired
    private ConsoleServlet ff4jServlet;

    @Autowired
    private FF4jDispatcherServlet ff4jDispatcherServlet;

    public static void main(String[] args) {
        SpringApplication.run(Ff4jSampleDynamodbApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean welcomeServlet() {
        return new ServletRegistrationBean(new WelcomeServlet(), "/");
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(ff4jServlet, "/ff4j-console/*");
    }

    @Bean
    public ServletRegistrationBean consoleVersion() {
        return new ServletRegistrationBean(ff4jDispatcherServlet, "/ff4j/*");
    }

}
