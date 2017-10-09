package org.ff4j.spring.cloudconfig.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Run this module as a StandAlone SpringCloudConfig provider.
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerStandAloneWithFF4jTest {

    /**
     * Main runtime.
     *
     * @param args
     *      no args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerStandAloneWithFF4jTest.class);
    }
}
