package org.ff4j.sample;

import static org.ff4j.audit.EventConstants.ACTION_CHECK_OK;
import static org.ff4j.audit.EventConstants.SOURCE_JAVA;
import static org.ff4j.audit.EventConstants.TARGET_FEATURE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * #%L
 * ff4j-sample-web
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
import org.ff4j.audit.Event;
import org.ff4j.audit.EventConstants;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.core.Feature;
import org.ff4j.utils.TimeUtils;
import org.ff4j.utils.Util;
import org.ff4j.web.FF4jProvider;

public class SimpleFF4jProvider implements FF4jProvider {

    /** ff4j instance. */
    private FF4j ff4j;
    
    private List < String > sources = new ArrayList<String>();
    
    private List < String > hostNames = new ArrayList<String>();
    
    private List < String > users = new ArrayList<String>();    
    
    // Utility to generate event
    protected Event generateFeatureUsageEvent(String uid) {
        Event evt = new Event(SOURCE_JAVA, TARGET_FEATURE, uid, ACTION_CHECK_OK);
        if (System.currentTimeMillis() % 2 == 0) {
            evt.setHostName(Util.getRandomElement(hostNames));
            evt.setSource(Util.getRandomElement(sources));
            evt.setUser( Util.getRandomElement(users));
        }
        return evt;
    }
    
    // Generate a random event during the period
    protected Event generateFeatureUsageEvent(String uid, long timestamp) {
        Event event = generateFeatureUsageEvent(uid);
        event.setTimestamp(timestamp);
        return event;
    }
    
    // Generate a random event during the period
    protected Event generateFeatureUsageEvent(String uid, long from, long to) {
        return generateFeatureUsageEvent(uid, from + (long) (Math.random() * (to-from)));
    }
    
    // Generate a random event during the period
    protected Event generateRandomFeatureUsageEvent(List < Feature > features, long from, long to) {
        return generateFeatureUsageEvent(Util.getRandomElement(features).getUid(), from , to);
    }
    
    // Populate repository for test
    public void populateRepository(int totalEvent) {
        EventRepository repo      = getFF4j().getEventRepository(); 
        
        List < Feature > features = new ArrayList<Feature>(getFF4j().getFeatures().values());
        for (int i = 0; i < totalEvent; i++) {
            repo.saveEvent(generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime(), 
                    TimeUtils.getTomorrowMidnightTime()));
        }

        List < String >  actions  = new ArrayList<String>(Arrays.asList(EventConstants.ACTION_CREATE, 
                EventConstants.ACTION_CREATE, EventConstants.ACTION_DELETE, EventConstants.ACTION_CONNECT));
        // Events for audit trail today
        for (int j = 0; j < 9; j++) {
            Event evt = generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime(), 
                    TimeUtils.getTomorrowMidnightTime());
            evt.setAction(Util.getRandomElement(actions));
            repo.saveEvent(evt);
        }
        // Events for audit trail 100 days
        for (int k = 0; k < 100; k++) {
            Event evt = generateRandomFeatureUsageEvent(features,
                    TimeUtils.getTodayMidnightTime() - (1000 * 3600) * 24 * 100 * k, 
                    TimeUtils.getTodayMidnightTime());
            evt.setAction(Util.getRandomElement(actions));
            repo.saveEvent(evt);
        }
        
    }

    /**
     * Default constructeur invoked by servlet.
     */
    public SimpleFF4jProvider() {
        
        if (ff4j == null) {
            // In Memory
            ff4j = new FF4j("ff4j.xml").audit(true).autoCreate(true);
            
            // Use JDBC DataSource
            //ff4j = new FF4j();
            
            /* DataSource
            BasicDataSource dbcpDataSource = new BasicDataSource();
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setUsername("root");
            dbcpDataSource.setPassword("****");
            dbcpDataSource.setUrl("jdbc:mysql://localhost:3306/ff4j_v1");*/
           
            // Core JDBC
            //ff4j.setFeatureStore(new JdbcFeatureStore(dbcpDataSource));
            //ff4j.setPropertiesStore(new JdbcPropertyStore(dbcpDataSource));
            //ff4j.setEventRepository(new JdbcEventRepository(dbcpDataSource));
            //ff4j.audit(true);
            
            // Spring JDBC
            //ff4j.setFeatureStore(new FeatureStoreSpringJdbc(dbcpDataSource));
            //ff4j.setPropertiesStore(new PropertyStoreSpringJdbc(dbcpDataSource));
            //ff4j.setEventRepository(new EventRepositorySpringJdbc(dbcpDataSource));
            //ff4j.audit(true);
            
            sources.add(SOURCE_JAVA);
            sources.add(EventConstants.SOURCE_SSH);
            sources.add(EventConstants.SOURCE_WEB);
            sources.add(EventConstants.SOURCE_WEBAPI);
            
            hostNames.add("node1");
            hostNames.add("node2");
            
            users.add("Pierre");
            users.add("Paul");
            users.add("Jacques");
            populateRepository(100);
        }
    }

    /**
     * Getter accessor for attribute 'fF4j'.
     *
     * @return current value of 'fF4j'
     */
    @Override
    public FF4j getFF4j() {
        return ff4j;
    }

}
