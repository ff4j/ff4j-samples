package org.ff4j.sample.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.core.FlippingStrategy;
import org.junit.Test;

public class RegionFlippingStrategyTest {

    // ff4j
    private final FF4j ff4j = new FF4j("ff4j-strategy-2.xml");

    // sample execution context
    private final FlippingExecutionContext fex = new FlippingExecutionContext();

    @Test
    public void testRegionStrategy() throws Exception {
        // Given
        assertTrue(ff4j.exist("notForEurop"));
        FlippingStrategy fs = ff4j.getFeature("notForEurop").getFlippingStrategy();
        assertTrue(fs.getClass() == RegionFlippingStrategy.class);
        assertEquals("ASIA,AMER", fs.getInitParams().get("grantedRegions"));

        // When
        fex.addValue(RegionFlippingStrategy.PARAMNAME_USER_REGION, "AMER");
        // Then
        assertTrue(ff4j.check("notForEurop", fex));

        // When
        fex.addValue(RegionFlippingStrategy.PARAMNAME_USER_REGION, "EUROP");
        // Then
        assertFalse(ff4j.check("notForEurop", fex));

    }

}
