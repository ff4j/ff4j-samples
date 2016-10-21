package org.ff4j.sample.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.ClientFilterStrategy;
import org.junit.Test;

public class ClientListStrategyTest {

    // initialize ff4j
    FF4j ff4j = new FF4j("ff4j-strategy-clientfilter.xml");

    @Test
    public void testClientFilter() {
        // Given
        assertTrue(ff4j.exist("pingCluster"));
        assertTrue(ff4j.getFeature("pingCluster").isEnable());

        // When no host provided, Then error
        try {
            assertFalse(ff4j.check("pingCluster"));
            fail(); // error as parameter not in execution context
        } catch (IllegalArgumentException iae) {
            assertTrue(iae.getMessage().contains(ClientFilterStrategy.CLIENT_HOSTNAME));
        }

        // When invalid host provided, Then unavailable
        FlippingExecutionContext fex = new FlippingExecutionContext();
        fex.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, "invalid");
        assertFalse(ff4j.check("pingCluster", fex));

        // When correct hostname... OK
        fex.addValue(ClientFilterStrategy.CLIENT_HOSTNAME, "srvprd01");
        assertTrue(ff4j.check("pingCluster", fex));
    }
}
