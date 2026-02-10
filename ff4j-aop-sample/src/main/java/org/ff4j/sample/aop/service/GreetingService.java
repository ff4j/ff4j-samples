package org.ff4j.sample.aop.service;

import org.ff4j.aop.Flip;

public interface GreetingService {

  @Flip(name = "language", alterBean = "greeting.french")
  String sayHello(String name);
}
