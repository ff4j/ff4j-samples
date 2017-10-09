package org.ff4j.sample.solution1;

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
public class JustToCheckTestSolution1 {

    @Configuration
    @ComponentScan(value = {"org.ff4j.sample.solution1", "org.ff4j.aop"})
    public static class SpringConfig {}
    
    @Autowired
    private FF4j ff4j;
    
    @Autowired
    private JustToCheck checker;
    
    @Test
    public void reproduceSergiiSample() {
        Assert.assertFalse(ff4j.check("check-no-logging"));
        
        // Default behavior
        checker.log2();
        
        // Flipping
        ff4j.enable("check-no-logging");
        
        // Now it's good
        checker.log2();
    }
    
}
