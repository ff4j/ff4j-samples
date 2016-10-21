package org.ff4j.springboot;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.ff4j.web.ApiConfig;
import org.ff4j.web.api.FF4jApiApplicationJersey2x;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class FF4JSampleWebApi extends FF4jApiApplicationJersey2x {
    
    @Autowired
    public ApiConfig apiConfig;
   
    @Override
    public ApiConfig getWebApiConfiguration() {
        return apiConfig;
    }
    
    @PostConstruct
    public void initialized() {
        super.init();
    }

}

