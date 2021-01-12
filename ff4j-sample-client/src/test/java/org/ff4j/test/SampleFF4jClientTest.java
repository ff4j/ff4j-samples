package org.ff4j.test;

import java.util.Map;

import org.ff4j.FF4j;
import org.ff4j.cache.FF4jCacheProxy;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.core.Feature;
import org.ff4j.web.jersey2.store.FeatureStoreHttp;
import org.ff4j.web.jersey2.store.PropertyStoreHttp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SampleFF4jClientTest {
    
    private static FF4j ff4j;
    
    @BeforeAll
    public static void initFF4j() {
        String ff4jApiEndPoint = "https://ff4j-demo.herokuapp.com/api/ff4j";
        FeatureStoreHttp  fStore = new FeatureStoreHttp(ff4jApiEndPoint);
        PropertyStoreHttp pStore = new PropertyStoreHttp(ff4jApiEndPoint);
        /* 
         * Maybe we don't want to do an http call each time we test a feature
         * as there is latency. Local in memory cache with TTL 10min can help.
         */
        FF4jCacheProxy cc = new FF4jCacheProxy(fStore, pStore, new InMemoryCacheManager(600));

        ff4j = new FF4j();
        ff4j.setFeatureStore(cc);
        ff4j.setPropertiesStore(cc);
        ff4j.audit(false); //(auditing at server level)
        ff4j.autoCreate(true);
    }
    
    @Test
    public void useFF4j() {
        System.out.println("-----------------------------");
        System.out.println("--     FF4J FEATURES       --");
        System.out.println("-----------------------------");
        Map<String, Feature> allFeatures = ff4j.getFeatureStore().readAll();
        for(Feature f : allFeatures.values()) {
            System.out.println("- Feature " + f.getUid() + "=>" + f.toJson());
        }
        
        if (ff4j.check("AwesomeFeature")) {
            System.out.println("'AwesomeFeature' is ON");
        } else {
            System.out.println("'AwesomeFeature' is OFF");
        }
        
    }

}
