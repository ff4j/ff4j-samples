package org.ff4j.spring.cloudconfig.app;

import org.ff4j.FF4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfiguration {
    
    @Bean
    public FF4j ff4j() {
        FF4j ff4j = new FF4j("ff4j-properties.xml");
        System.out.println(ff4j.getProperties());
        return ff4j;
    }

}
