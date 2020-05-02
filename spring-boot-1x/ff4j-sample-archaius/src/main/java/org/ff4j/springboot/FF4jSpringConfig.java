package org.ff4j.springboot;

/*
 * #%L
 * ff4j-sample-archaius
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.ff4j.FF4j;
import org.ff4j.archaius.PropertyStoreArchaius;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jSpringConfig {
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new InMemoryFeatureStore("ff4j.xml"));
        ff4j.setPropertiesStore(new PropertyStoreArchaius(new InMemoryPropertyStore("ff4j.xml")));
        ff4j.audit(false);
        return ff4j;
    }
    
    @Bean
    public ConsoleServlet getFF4JServlet() {
        ConsoleServlet cs = new ConsoleServlet();
        cs.setFf4j(getFF4j());
        return cs;
    }
    
    @Bean
    public FF4jDispatcherServlet getFF4jServletBis() {
        FF4jDispatcherServlet servlet = new FF4jDispatcherServlet();
        servlet.setFf4j(getFF4j());
        return servlet;
    }
    
    @Bean
    public ApiConfig getApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setFF4j(getFF4j());
        return apiConfig;
    }

}
