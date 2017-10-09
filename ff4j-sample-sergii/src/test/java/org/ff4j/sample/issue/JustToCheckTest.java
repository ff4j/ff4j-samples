package org.ff4j.sample.issue;

import org.ff4j.FF4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Answering : https://stackoverflow.com/questions/46525622/ff4j-is-not-working-for-spring?utm_source=dlvr.it&utm_medium=twitter
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JustToCheckTest {

    @Configuration
    @ComponentScan(value = {"org.ff4j.sample.issue", "org.ff4j.aop"})
    public static class SpringConfig {}
    
    @Autowired
    private FF4j ff4j;
    
    @Autowired
    private JustToCheck checker;
    
    @Test
    public void reproduceSergiiSample() {
        // Unfortunately with both enable and disable it will be logged
        Assert.assertFalse(ff4j.check("check-no-logging"));
        checker.log2();
        ff4j.enable("check-no-logging");
        // Still hello2
        checker.log2();
        
    }
    
}
