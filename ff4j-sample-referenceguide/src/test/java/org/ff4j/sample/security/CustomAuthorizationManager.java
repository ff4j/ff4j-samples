package org.ff4j.sample.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ff4j.security.AuthorizationsManager;

public class CustomAuthorizationManager implements AuthorizationsManager {

    public static ThreadLocal<String> currentUserThreadLocal = new ThreadLocal<String>();

    private static final Map<String, Set<String>> permissions = new HashMap<String, Set<String>>();

    static {
        permissions.put("userA", new HashSet<String>(Arrays.asList("user", "admin", "beta")));
        permissions.put("userB", new HashSet<String>(Arrays.asList("user")));
        permissions.put("userC", new HashSet<String>(Arrays.asList("user", "beta")));
    }

    /** {@inheritDoc} */
    public Set<String> getCurrentUserPermissions() {
        String currentUser = currentUserThreadLocal.get();
        return permissions.containsKey(currentUser) ? permissions.get(currentUser) : new HashSet<String>(); 
    }

    /** {@inheritDoc} */
    public Set<String> listAllPermissions() {
        // Not calculated each time in fact...
        Set<String> allPermissions = new HashSet<String>();
        for (Set<String> subPersmission : permissions.values()) {
            allPermissions.addAll(subPersmission);
        }
        return allPermissions;
    }

    /** {@inheritDoc} */
    public String toJson() {
        return toString();
    }

	public String getCurrentUserName() {
		return currentUserThreadLocal.get();
	}

}
