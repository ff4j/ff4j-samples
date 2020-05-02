package org.ff4j.springboot.conf;

/*
 * #%L
 * ff4j-sample-springboot
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
import org.ff4j.core.Feature;
import org.ff4j.property.PropertyInt;
import org.ff4j.property.PropertyString;
import org.ff4j.strategy.el.ExpressionFlipStrategy;
import org.ff4j.utils.Util;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({FF4j.class})
@ComponentScan(value = {"org.ff4j.spring.boot.web.api", "org.ff4j.services", "org.ff4j.aop", "org.ff4j.spring"})
public class FF4jConfiguration {
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Bean
    public FF4j getFF4j() {
       FF4j ff4j = new FF4j()
            .createFeature("f1")
            .createFeature("AwesomeFeature")
            .createFeature("f2").createFeature("f3")
            .createProperty(new PropertyString("SampleProperty", "go!"))
            .createProperty(new PropertyInt("SamplePropertyIn", 12));
       
        Feature exp = new Feature("exp");
        exp.setFlippingStrategy(new ExpressionFlipStrategy("exp", "f1 & f2 | !f1 | f2"));
        ff4j.createFeature(exp);
        return ff4j;
    }

    @Bean
    public FF4jDispatcherServlet getFF4JServlet() {
        FF4jDispatcherServlet ds = new FF4jDispatcherServlet();
        ds.setFf4j(getFF4j());
        return ds;
    }
    
    @Bean
    public ApiConfig getApiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        
        // Enable Authentication on API KEY
        apiConfig.setAuthenticate(false);
        apiConfig.createApiKey("apikey1", true, true, Util.set("ADMIN", "USER"));
        apiConfig.createApiKey("apikey2", true, true, Util.set("ADMIN", "USER"));
        apiConfig.createUser("userName","password", true, true, Util.set("ADMIN", "USER"));
        apiConfig.createUser("user","userPass", true, true, Util.set("ADMIN", "USER"));
        apiConfig.createUser("a","a", true, true, Util.set("ADMIN", "USER"));
        apiConfig.createUser("b","b", true, true, Util.set("ADMIN", "USER"));
        
        // Check Autorization as well
        apiConfig.setAutorize(false);
        apiConfig.setWebContext("/api");
        apiConfig.setPort(8080);
        apiConfig.setFF4j(getFF4j());
        return apiConfig;
    }

}
