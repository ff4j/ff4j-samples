package org.ff4j.sample.aop.service;

import org.springframework.stereotype.Component;

@Component("greeting.french")
public class FrenchGreetingService implements GreetingService {

  @Override
  public String sayHello(String name) {
    return "Bonjour, " + name + "!";
  }
}