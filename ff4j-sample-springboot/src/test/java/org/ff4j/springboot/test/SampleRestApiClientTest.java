package org.ff4j.springboot.test;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.web.jersey2.store.FeatureStoreHttp;
import org.ff4j.web.jersey2.store.PropertyStoreHttp;
import org.junit.Test;

/**
 * Sample tests to work with REST API.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class SampleRestApiClientTest {

    private FF4j ff4j;
    
    @Test
    public void setupFF4j() {
        
        String targetRestApiURL = "http://localhost:8080/api/ff4j/";
        
        // Init FF4j as HTTP CLIENT
        ff4j = new  FF4j();
        ff4j.setFeatureStore(new FeatureStoreHttp(targetRestApiURL, "user", "userPass"));
        ff4j.setPropertiesStore(new PropertyStoreHttp(targetRestApiURL, "user", "userPass"));
        
        Feature f1 = ff4j.getFeatureStore().read("f1");
        System.out.println(f1);
        
        // EXPECT AUDITING AND SECURITY
        
    }
}
