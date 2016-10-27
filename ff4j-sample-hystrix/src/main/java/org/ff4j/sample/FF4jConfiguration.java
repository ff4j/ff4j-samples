package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.property.PropertyInt;
import org.ff4j.property.PropertyString;
import org.ff4j.sample.hystrix.FF4JHelper;
import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.FF4jProvider;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="org.ff4j.aop")
public class FF4jConfiguration implements FF4jProvider {
    
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
        return new FF4j()
            .createFeature(FF4JHelper.FEATURE_UID_F1)
            .createProperty(new PropertyString("SampleProperty", "go!"))
            .createProperty(new PropertyInt("SamplePropertyIn", 12));
    } 
    
    @Bean
    public FF4jDispatcherServlet getFF4jDispatcherServlet() {
        FF4jDispatcherServlet servlet = new FF4jDispatcherServlet();
        servlet.setFf4j(getFF4j());
        return servlet;
    }
    
    @Bean
    public ConsoleServlet getOldDispatcherServlet() {
        ConsoleServlet servlet = new ConsoleServlet();
        servlet.setFf4j(getFF4j());
        return servlet;
    }
    

}
