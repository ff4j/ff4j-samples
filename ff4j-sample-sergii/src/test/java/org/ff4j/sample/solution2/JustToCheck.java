package org.ff4j.sample.solution2;

import org.ff4j.aop.Flip;
import org.springframework.stereotype.Component;

@Component
public interface JustToCheck {
    
    @Flip(name="check-no-logging", alterBean="just-to-check")
    void log2();

}
