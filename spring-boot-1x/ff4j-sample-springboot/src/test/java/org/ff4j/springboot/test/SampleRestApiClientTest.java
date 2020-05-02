package org.ff4j.springboot.test;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.web.jersey2.store.FeatureStoreHttp;
import org.ff4j.web.jersey2.store.PropertyStoreHttp;
<<<<<<< HEAD:spring-boot-1x/ff4j-sample-springboot/src/test/java/org/ff4j/springboot/test/SampleRestApiClientTest.java
import org.junit.Assert;
=======
import org.junit.ClassRule;
>>>>>>> 6d18dbfff92c97bdcdcce828910e9974431e35e7:ff4j-sample-springboot/src/test/java/org/ff4j/springboot/test/SampleRestApiClientTest.java
import org.junit.Ignore;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * Sample tests to work with REST API.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Ignore
public class SampleRestApiClientTest {

    private FF4j ff4j;

    @Test
    public void setupFF4j() {
        String targetRestApiURL = "http://localhost:8080/api/ff4j/";
        // Given
        ff4j = new  FF4j();
        ff4j.setFeatureStore(new FeatureStoreHttp(targetRestApiURL, "user", "userPass"));
        ff4j.setPropertiesStore(new PropertyStoreHttp(targetRestApiURL, "user", "userPass"));
        
        // When
        ff4j.createFeature(new Feature("f1"));
        // Then
        Assert.assertNotNull(ff4j.getFeatureStore().read("f1"));
        
    }
}
