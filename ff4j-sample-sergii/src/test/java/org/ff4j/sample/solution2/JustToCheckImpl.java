package org.ff4j.sample.solution2;

import org.springframework.stereotype.Component;

@Component("just-to-check")
public class JustToCheckImpl implements JustToCheck {
    
    public void log2() {
        System.out.println("hello2");
    }
}
