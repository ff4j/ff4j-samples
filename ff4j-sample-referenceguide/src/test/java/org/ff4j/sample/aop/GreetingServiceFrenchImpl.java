package org.ff4j.sample.aop;

import org.springframework.stereotype.Component;

@Component("greeting.french")
public class GreetingServiceFrenchImpl implements GreetingService {
    public String sayHello(String name) {
        return "Bonjour " + name;
    }
}
