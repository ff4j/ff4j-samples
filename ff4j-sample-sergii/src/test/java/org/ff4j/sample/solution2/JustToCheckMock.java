package org.ff4j.sample.solution2;

import org.springframework.stereotype.Component;

@Component("just-to-check-mock")
public class JustToCheckMock implements JustToCheck {
    
    public void log2() {
        System.out.println("As check-no-logging is disable... do nothing");
    }
}
