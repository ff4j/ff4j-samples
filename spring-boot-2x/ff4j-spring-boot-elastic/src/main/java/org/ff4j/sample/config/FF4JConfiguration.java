/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2013-2016 the original author or authors.
 */

package org.ff4j.sample.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.ff4j.FF4j;
import org.ff4j.elastic.ElasticQueryHelper;
import org.ff4j.elastic.store.EventRepositoryElastic;
import org.ff4j.elastic.store.FeatureStoreElastic;
import org.ff4j.elastic.store.PropertyStoreElastic;
import org.ff4j.spring.boot.web.api.config.EnableFF4jSwagger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.searchbox.client.JestClient;

/**
 * Created by Paul
 *
 * Adapt for a demo on Elastic.
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 * @author Cedrick LUNVEN (@clunven)
 */
@Configuration
@EnableFF4jSwagger
public class FF4JConfiguration {
    
    @Value("${elastic.url}")
    private String elasticUrl;
    
    @Bean
    public JestClient jestClient() {
        try {
            return ElasticQueryHelper.createDefaultJestClient(new URL(elasticUrl));
        } catch (MalformedURLException e) {
           throw new IllegalArgumentException(
                   "Invalid url for elastic "+ 
                   "please check 'elastic.url' property",e);
        }
    }
    
    /**
     * Initiate ff4j pointing to Elastic.
     *
     * Default index is ff4j_features but you can override the indexName
     * ff4j.setFeatureStore(new FeatureStoreElastic(jestClient, "indexName"));
     * 
     * @param jestClient
     * @return
     */
    @Bean
    public FF4j getFF4j(JestClient jestClient) {
        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new FeatureStoreElastic(jestClient));
        ff4j.setPropertiesStore(new PropertyStoreElastic(jestClient));
        ff4j.setEventRepository(new EventRepositoryElastic(jestClient));
        return ff4j;
    }
}

