package org.ff4j.sample;

import java.util.HashSet;
import java.util.Set;

import org.ff4j.security.AbstractAuthorizationManager;

public class MockAuthorisationManager extends AbstractAuthorizationManager {

    @Override
    public String getCurrentUserName() {
        return "someUser";
    }

    /** {@inheritDoc} */
    @Override
    public Set<String> getCurrentUserPermissions() {
        return new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override
    public Set<String> listAllPermissions() {
        Set<String> auths = new HashSet<>();
        auths.add("Administrators");
        auths.add("Users");
        auths.add("SuperUsers");
        return auths;
    }

}
