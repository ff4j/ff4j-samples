package org.ff4j.sample.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.ff4j.FF4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*applicationContext-security.xml"})
public class SampleSpringSecurityTest {

    @Autowired
    private FF4j ff4j;

    /** Security context. */
    private SecurityContext securityCtx;

    @Before
    public void setUp() throws Exception {
        securityCtx = SecurityContextHolder.getContext();

        // UserA got the roles : beta, user, admin
        List<GrantedAuthority> listOfRoles = new ArrayList<GrantedAuthority>();
        listOfRoles.add(new SimpleGrantedAuthority("beta"));
        User userA = new User("userA", "passwdA", listOfRoles);

        // Creadentials for UserA
        String userName = userA.getUsername();
        String passwd = userA.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, passwd, listOfRoles);
        token.setDetails(userA);

        // Create a security context with
        SecurityContext context = new SecurityContextImpl();
        context.setAuthentication(token);
        SecurityContextHolder.setContext(context);
        // <--
    }

    @Test
    public void testIsAuthenticatedAndAuthorized() {

        // Given userA is authenticated in Spring
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Assert.assertTrue(auth.isAuthenticated());

        // UserA has not expected role 'admin'
        assertTrue(ff4j.exist("sayHello"));
        assertTrue(ff4j.getFeature("sayHello").isEnable());
        assertTrue(ff4j.getFeature("sayHello").getPermissions().contains("admin"));
        assertFalse(ff4j.check("sayHello"));

        // UserA has expected role 'beta'
        assertTrue(ff4j.exist("sayGoodBye"));
        assertTrue(ff4j.getFeature("sayGoodBye").isEnable());
        assertTrue(ff4j.getFeature("sayGoodBye").getPermissions().contains("beta"));
        assertTrue(ff4j.check("sayGoodBye"));
    }


    @After
    public void tearDown() {
        SecurityContextHolder.setContext(securityCtx);
    }

}
