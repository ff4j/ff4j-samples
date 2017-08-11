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

import java.util.Properties;
import org.ff4j.FF4j;
import org.ff4j.hazelcast.store.FeatureStoreHazelCast;
import org.ff4j.hazelcast.store.PropertyStoreHazelCast;
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

    @Value("${server.port}")
    private Integer serverPort;

    @Bean
    public FF4j getFF4j() {

        Properties properties = System.getProperties();
        FF4j ff4j = new FF4j();

        ff4j.setFeatureStore(new FeatureStoreHazelCast("hazelcast-default.xml", properties));
        ff4j.setPropertiesStore(new PropertyStoreHazelCast("hazelcast-default.xml", properties));

        ff4j.autoCreate(true);
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
        apiConfig.setAuthenticate(authentication);
        apiConfig.setAutorize(authorization);
        apiConfig.setWebContext("/");
        apiConfig.setPort(serverPort);
        apiConfig.setFF4j(getFF4j());
        return apiConfig;
    }

}
