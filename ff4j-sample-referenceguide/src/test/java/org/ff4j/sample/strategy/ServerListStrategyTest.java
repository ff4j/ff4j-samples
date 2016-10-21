package org.ff4j.sample.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.ServerFilterStrategy;
import org.junit.Test;

public class ServerListStrategyTest {

    // initialize ff4j
    FF4j ff4j = new FF4j("ff4j-strategy-serverfilter.xml");

    @Test
    public void testServerFilter() throws UnknownHostException {
        // Given
        assertTrue(ff4j.exist("onlyOnPRODServers"));
        assertTrue(ff4j.getFeature("onlyOnPRODServers").isEnable());

        // When invalid host provided, Then unavailable
        FlippingExecutionContext fex = new FlippingExecutionContext();
        fex.addValue(ServerFilterStrategy.SERVER_HOSTNAME, "invalid");
        assertFalse(ff4j.check("onlyOnPRODServers", fex));

        // When correct hostname... OK
        fex.addValue(ServerFilterStrategy.SERVER_HOSTNAME, "srvprd01");
        assertTrue(ff4j.check("onlyOnPRODServers", fex));

        // When no host provided, Then try to identified by itself but not SECURE
        System.out.println("Trying..." + InetAddress.getLocalHost().getHostName() + " against white list");
        // my laptop hostname is not in the whitelist
        assertFalse(ff4j.check("onlyOnPRODServers"));
    }
}
