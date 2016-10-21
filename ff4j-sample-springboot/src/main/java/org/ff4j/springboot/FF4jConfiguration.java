package org.ff4j.springboot;

import org.ff4j.FF4j;
import org.ff4j.property.Property;
import org.ff4j.property.PropertyInt;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfiguration {
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
        return new FF4j()
            .createFeature("f1")
            .createFeature("f2").createFeature("f3")
            .createProperty(new Property("SampleProperty", "go!"))
            .createProperty(new PropertyInt("SamplePropertyIn", 12));
    }
    
    @Bean
    public ConsoleServlet getFF4JServlet() {
        ConsoleServlet cs = new ConsoleServlet();
        cs.setFf4j(getFF4j());
        return cs;
    }
    
    @Bean
    public ApiConfig getApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setfF4j(getFF4j());
        return apiConfig;
    }

}
