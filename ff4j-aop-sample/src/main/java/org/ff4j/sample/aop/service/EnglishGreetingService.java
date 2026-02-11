package org.ff4j.sample.aop.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("greeting.english")
public class EnglishGreetingService implements GreetingService {

  @Override
  public String sayHello(String name) {
    return "Hello, " + name + "!";
  }
}
