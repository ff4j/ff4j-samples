package org.ff4j.sample.config;

import org.ff4j.FF4j;
import org.ff4j.security.SpringSecurityAuthorisationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfig {
    
    @Bean
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();
        // Look for permissions and all things in Spring Security
        ff4j.setAuthorizationsManager(new SpringSecurityAuthorisationManager());
        ff4j.audit(true);
        return ff4j;
    }
 
}
