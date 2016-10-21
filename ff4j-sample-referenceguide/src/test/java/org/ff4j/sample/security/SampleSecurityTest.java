package org.ff4j.sample.security;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.ff4j.FF4j;
import org.ff4j.security.AuthorizationsManager;
import org.junit.Test;

public class SampleSecurityTest {

    @Test
    public void sampleSecurityTest() {

        // Create FF4J
        FF4j ff4j = new FF4j("ff4j-security.xml");
        // Add the Authorization Manager Filter
        AuthorizationsManager authManager = new CustomAuthorizationManager();
        ff4j.setAuthorizationsManager(authManager);
        // <---

        // Given : Feature exist and enable
        assertTrue(ff4j.exist("sayHello"));
        assertTrue(ff4j.getFeature("sayHello").isEnable());

        // Unknow user does not have any permission => check is false
        CustomAuthorizationManager.currentUserThreadLocal.set("unknown-user");
        System.out.println(authManager.getCurrentUserPermissions());
        assertFalse(ff4j.check("sayHello"));

        // userB exist bit he has not role Admin
        CustomAuthorizationManager.currentUserThreadLocal.set("userB");
        System.out.println(authManager.getCurrentUserPermissions());
        assertFalse(ff4j.check("sayHello"));

        // userA is admin
        CustomAuthorizationManager.currentUserThreadLocal.set("userA");
        System.out.println(authManager.getCurrentUserPermissions());
        assertTrue(ff4j.check("sayHello"));

    }
    
}