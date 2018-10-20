package org.ff4j.voxxeddays.conf;

import org.ff4j.FF4j;
import org.ff4j.cassandra.CassandraConnection;
import org.ff4j.cassandra.store.EventRepositoryCassandra;
import org.ff4j.consul.ConsulConnection;
import org.ff4j.consul.store.FeatureStoreConsul;
import org.ff4j.consul.store.PropertyStoreConsul;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orbitz.consul.Consul;

@Configuration
public class FF4jConfig {
    
    // Connectivity to consul
    @Value("${spring.cloud.consul.host:localhost}")
    private String consulHost;
    @Value("${spring.cloud.consul.port:8500}")
    private int consulPort;

    // Connectivity to Cassandra
    @Value("${cassandra.host:localhost}")
    private String cassandraHost;
    @Value("${cassandra.port:9042}")
    private int cassandraPort;
    
    @Bean
    public FF4j ff4j() {
        FF4j ff4j = new FF4j().audit(true).autoCreate(true);
        
        // Features in Consul
        ConsulConnection connection = new ConsulConnection(
                Consul.builder().withUrl("http://" + consulHost + ":" + consulPort).build());       
        ff4j.setFeatureStore(new FeatureStoreConsul(connection));
        ff4j.setPropertiesStore(new PropertyStoreConsul(connection));
        
        // Events into Cassandra 
        ff4j.setEventRepository(
                    new EventRepositoryCassandra(
                           new CassandraConnection(cassandraHost, cassandraPort)));
        return ff4j;
    }
}
