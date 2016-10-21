package org.ff4j.sample.aop;

import org.springframework.stereotype.Component;

@Component("greeting.english")
public class GreetingServiceEnglishImpl implements GreetingService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}