package org.ff4j.sample.aop.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("greeting.english")
@Primary
public class EnglishGreetingService implements GreetingService {

  @Override
  public String sayHello(String name) {
    return "Hello, " + name + "!";
  }
}
