package org.ff4j.voxxeddays.conf;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.consul.ConsulConnection;
import org.ff4j.consul.store.FeatureStoreConsul;
import org.ff4j.consul.store.PropertyStoreConsul;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.orbitz.consul.Consul;

@Configuration
public class FF4jConfig {
    
    @Value("${spring.cloud.consul.host:localhost}")
    private String consulHost;
    
    @Value("${spring.cloud.consul.port:5000}")
    private int consulPort;
    
    @Bean
    public Consul consul() {
        return Consul.builder().withUrl("http://" + consulHost + ":" + consulPort).build();
    }
    
    @Bean
    public FF4j ff4j(Consul c) {
        FF4j ff4j = new FF4j().audit(true).autoCreate(true);
        ConsulConnection connection = new ConsulConnection(c);
        ff4j.setFeatureStore(new FeatureStoreConsul(connection));
        ff4j.setPropertiesStore(new PropertyStoreConsul(connection));
        ff4j.setEventRepository(new InMemoryEventRepository());
        return ff4j;
    }

}
