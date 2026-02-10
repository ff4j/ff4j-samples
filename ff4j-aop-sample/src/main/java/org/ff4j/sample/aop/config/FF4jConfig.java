package org.ff4j.sample.aop.config;

import org.ff4j.FF4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"org.ff4j.aop", "org.ff4j.sample.aop"})
public class FF4jConfig {

  @Bean
  public FF4j ff4j() {
    return new FF4j("ff4j-language-sample.xml");
  }
}
