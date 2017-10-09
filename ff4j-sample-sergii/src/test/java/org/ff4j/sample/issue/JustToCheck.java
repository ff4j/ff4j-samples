package org.ff4j.sample.issue;

import org.ff4j.aop.Flip;
import org.springframework.stereotype.Component;

@Component("just-to-check")
public class JustToCheck {
    
    @Flip(name="check-no-logging")
    public void log2() {
        System.out.println("hello2");
    }

    @Flip(name="check-logging")
    public void log3() {
        System.out.println("hello3");
    }
}
