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

import org.ff4j.FF4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Paul
 *
 * @author <a href="mailto:paul58914080@gmail.com">Paul Williams</a>
 */
@Configuration
public class FF4JConfiguration {
    
    @Bean
    public FF4j getFF4j() {
        // You cen define ff4j the way you like
        // the simplest is to use XML and InMemory but there are dozens of DB available.
        return new FF4j("ff4j-features.xml");
        
        // Please add ff4j-store-springjdbc for this sample to work..and a Datasource
        //FF4j ff4j = new FF4j();
        //ff4j.setFeatureStore(new FeatureStoreSpringJdbc(myDataSource));
        //ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(myDataSource));
        //ff4j.setEventRepository(new EventRepositorySpringJdbc(myDataSource));
        // Enable auditing
        //ff4j.audit(true);
        // If feature not found in DB, automatically created (as false)
        //ff4j.autoCreate(enableAutoCreate);
    }
}

