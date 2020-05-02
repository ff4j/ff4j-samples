package org.ff4j.sample.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.mongo.store.FeatureStoreMongo;
import org.ff4j.mongo.store.PropertyStoreMongo;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class MongoDbSampleUsage {

    @Test
    public void sampleMongo() {

        // Define Connectivity to DB (see with authorization - Using MongoDriver
        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // Use Several nodes and authentication
        MongoCredential credential = MongoCredential.createMongoCRCredential("username", "FF4J", "password".toCharArray());
        ServerAddress adr = new ServerAddress("localhost", 22012);
        MongoClient mongoClient2 = new MongoClient(adr, Arrays.asList(credential));

        // Using Spring-data-mongodb
        MongoDatabase mongoDatabase = mongoClient.getDatabase("ff4j");

        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(new FeatureStoreMongo(mongoDatabase.getCollection("features")));
        ff4j.setPropertiesStore(new PropertyStoreMongo(mongoDatabase.getCollection("properties")));
        // Not audit implementation yet

        // Fetch Data
        Map<String, Feature> samples = ff4j.getFeatureStore().readAll();


    }

}
