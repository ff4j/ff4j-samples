package org.ff4j.sample.aop;

import org.ff4j.FF4j;
import org.ff4j.sample.aop.config.FF4jConfig;
import org.ff4j.sample.aop.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = FF4jConfig.class)
class FF4jAopTest {

  @Autowired
  private FF4j ff4j;

  @Autowired
  private GreetingService greetingService;

  @Test
  void defaultEnglishGreeting() {
    // Given
    assertThat(ff4j.getFeatureStore().exist("language")).isTrue();
    assertThat(ff4j.check("language")).isFalse();
    // When
    String greeting = greetingService.sayHello("John");
    // Then
    assertThat(greeting).isEqualTo("Hello, John!");
  }

  @Test
  void toggleFrenchGreeting() {
    // Given
    assertThat(ff4j.getFeatureStore().exist("language")).isTrue();
    assertThat(ff4j.check("language")).isFalse();
    // When
    ff4j.enable("language");
    // Then
    assertThat(ff4j.check("language")).isTrue();
    // When
    String greeting = greetingService.sayHello("John");
    // Then
    assertThat(greeting).isEqualTo("Bonjour, John!");
  }
}
