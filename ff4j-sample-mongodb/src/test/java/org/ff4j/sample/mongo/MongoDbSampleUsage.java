package org.ff4j.sample.mongo;

import java.util.Arrays;
import java.util.Map;

import org.bson.Document;
import org.ff4j.FF4j;
import org.ff4j.audit.repository.EventRepository;
import org.ff4j.core.Feature;
import org.ff4j.core.FeatureStore;
import org.ff4j.property.store.PropertyStore;
import org.ff4j.store.FeatureStoreMongoCollection;
import org.ff4j.store.PropertyStoreMongoCollection;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDbSampleUsage {
	
	@Test
	public void sampleMongo() {
		
	    // Define Connectivity to DB (see with authorization - Using MongoDriver 
	    MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	    
	    // Use Several nodes and authentication
	    MongoCredential credential = MongoCredential.createMongoCRCredential("username", "FF4J", "password".toCharArray());
        ServerAddress adr = new ServerAddress("localhost", 22012);
        MongoClient mongoClient2 = new MongoClient(adr, Arrays.asList(credential));
	    
        // Using Spring-data-mongodb
        MongoDatabase mongoDatabase = mongoClient.getDatabase("ff4j");
        
		FF4j ff4j = new FF4j();
		ff4j.setFeatureStore(new FeatureStoreMongoCollection(mongoDatabase.getCollection("features")));
		ff4j.setPropertiesStore(new PropertyStoreMongoCollection(mongoDatabase.getCollection("properties")));
        // Not audit implementation yet
		
		// Fetch Data
		Map < String, Feature > samples = ff4j.getFeatureStore().readAll();
		
		
	}

}
