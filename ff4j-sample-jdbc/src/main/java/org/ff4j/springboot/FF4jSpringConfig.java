package org.ff4j.springboot;

import org.apache.commons.dbcp2.BasicDataSource;

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
import org.ff4j.springjdbc.store.FeatureStoreSpringJdbc;
import org.ff4j.springjdbc.store.PropertyStoreSpringJdbc;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jSpringConfig {
 
    @Value("${ff4j.webapi.authentication}")
    private boolean authentication = false;
    
    @Value("${ff4j.webapi.authorization}")
    private boolean authorization = false;
    
    @Value("${ff4j.jdbc.driverClassName}")
    private String jdbcDriverClassName;
    
    @Value("${ff4j.jdbc.url}")
    private String jdbcUrl;
    
    @Value("${ff4j.jdbc.user}")
    private String jdbcUser;
    
    @Value("${ff4j.jdbc.password}")
    private String jdbcPassword;
    
    @Bean
    public FF4j getFF4j() {
        
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        dataSource.setConnectionProperties("useUnicode=yes;characterEncoding=UTF-8;");
        
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new FeatureStoreSpringJdbc(dataSource));
        ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(dataSource));
        ff4j.audit(false);
        
        return ff4j;
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
