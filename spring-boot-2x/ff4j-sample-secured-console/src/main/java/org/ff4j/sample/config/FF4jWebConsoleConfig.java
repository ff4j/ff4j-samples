package org.ff4j.sample.config;

import org.ff4j.FF4j;
import org.ff4j.spring.boot.web.api.config.EnableFF4jSwagger;
import org.ff4j.web.FF4jDispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sample configuration for WebConsole and RestAPI
 * 
 * REST API:
 *  To enable REST API you need to provide annotation @EnableFF4jSwagger. Once done
 *  you can use the endpoint api/ff4j/.
 *  
 * WEB CONSOLE:
 *  the web console will be available at /ff4j-web-console/home/
 */
@Configuration
@EnableFF4jSwagger
@ConditionalOnClass({FF4jDispatcherServlet.class})
@AutoConfigureAfter(FF4jConfig.class)
@ConditionalOnProperty(value="ff4j.webconsole.enable", havingValue = "true", matchIfMissing = false)
public class FF4jWebConsoleConfig extends SpringBootServletInitializer implements WebMvcConfigurer {
    
    /** Logger for the class. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FF4jWebConsoleConfig.class);
     
    @Value("${ff4j.webconsole.url:/ff4j-web-console}")
    private String webConsoleUrl;
    
    @Bean
    @ConditionalOnMissingBean
    public FF4jDispatcherServlet getFF4jDispatcherServlet(FF4j ff4j) {
        LOGGER.info("Initializing the web console servlet as ff4j as been found in contexts", webConsoleUrl);
        FF4jDispatcherServlet ff4jConsoleServlet = new FF4jDispatcherServlet();
        ff4jConsoleServlet.setFf4j(ff4j);
        return ff4jConsoleServlet;
    }
   
    @Bean
    @SuppressWarnings({"rawtypes","unchecked"})
    public ServletRegistrationBean ff4jDispatcherServletRegistrationBean(FF4jDispatcherServlet ff4jDispatcherServlet) {
        LOGGER.info("Exposing FF4j web console on '{}' as property 'ff4j.webconsole.enable' is true", webConsoleUrl);
        return new ServletRegistrationBean(ff4jDispatcherServlet, webConsoleUrl + "/*");
    }
}
