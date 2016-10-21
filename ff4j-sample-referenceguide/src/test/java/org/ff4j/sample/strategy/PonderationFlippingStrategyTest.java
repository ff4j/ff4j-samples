package org.ff4j.sample.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.ff4j.FF4j;
import org.junit.Test;

public class PonderationFlippingStrategyTest {

    // initialize ff4j
    FF4j ff4j = new FF4j("ff4j-strategy-ponderation.xml");

    @Test
    public void testPonderation() {
        // Given (weight = 0)
        assertTrue(ff4j.exist("pond_0"));
        // Then => always false
        assertFalse(ff4j.check("pond_0"));

        // Given (weight = 100%)
        assertTrue(ff4j.exist("pond_1"));
        // Then => Always true
        assertTrue(ff4j.check("pond_1"));

        // Given (weight = 60%
        assertTrue(ff4j.exist("pond_06"));
        // When : Try 1 million times
        double success = 0.0;
        for (int i = 0; i < 1000000; i++) {
            if (ff4j.check("pond_06")) {
                success++;
            }
        }
        // Then, percentage ok with great precision
        double resultPercent = success / 1000000;
        assertTrue(resultPercent < (0.6 + 0.001));
        assertTrue(resultPercent > (0.6 - 0.001));
    }

}
