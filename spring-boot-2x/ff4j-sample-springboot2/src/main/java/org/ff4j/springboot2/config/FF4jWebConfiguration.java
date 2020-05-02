package org.ff4j.springboot2.config;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.ff4j.web.embedded.ConsoleServlet;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web configuration
 *
 * @author <a href="mailto:drizztguen77@gmail.com">Curtis White</a>
 */
@Configuration
@ConditionalOnClass({ConsoleServlet.class, FF4jDispatcherServlet.class})
@AutoConfigureAfter(FF4JConfiguration.class)
public class FF4jWebConfiguration extends SpringBootServletInitializer {

    @Bean
    public ServletRegistrationBean consoleVersion(FF4jDispatcherServlet ff4jDispatcherServlet) {
        return new ServletRegistrationBean<>(ff4jDispatcherServlet, "/ff4j-web-console/*");
    }

    @Bean
    @ConditionalOnMissingBean
    public FF4jDispatcherServlet getFF4jDispatcherServlet(FF4j ff4j) {
        FF4jDispatcherServlet ff4jDispatcherServlet = new FF4jDispatcherServlet();
        ff4jDispatcherServlet.setFf4j(ff4j);
        return ff4jDispatcherServlet;
    }

}
