package org.ff4j.sample.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingStrategy;
import org.ff4j.strategy.time.ReleaseDateFlipStrategy;
import org.junit.Test;

public class OverridingStrategyTest {

    // ff4j
    private final FF4j ff4j = new FF4j("ff4j-strategy-1.xml");

    @Test
    public void testBehaviourOfOverriding() {
        assertTrue(ff4j.exist("sayHello"));

        // Behaviour of the strategy
        FlippingStrategy fs = ff4j.getFeature("sayHello").getFlippingStrategy();
        assertTrue(fs.getClass() == OfficeHoursFlippingStrategy.class);
        assertEquals("9", fs.getInitParams().get("startDate"));
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        boolean isNowOfficeTime = (hour > 9) & (hour < 18);
        assertEquals(isNowOfficeTime, ff4j.check("sayHello"));

        // New Strategy : ReleaseDate with date in the past ==> Always true
        FlippingStrategy newStrategy = new ReleaseDateFlipStrategy(new Date(System.currentTimeMillis() - 100000));
        assertTrue(ff4j.checkOveridingStrategy("sayHello", newStrategy, null));
    }

}
