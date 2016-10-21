package org.ff4j.sample;

/*
 * #%L
 * ff4j-sample-web
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.HashSet;
import java.util.Set;

import org.ff4j.security.AbstractAuthorizationManager;

public class MockAuthorisationManager extends AbstractAuthorizationManager {

    /** {@inheritDoc} */
    public Set<String> getCurrentUserPermissions() {
        return new HashSet<String>();
    }

    /** {@inheritDoc} */
    public Set<String> listAllPermissions() {
        Set<String> auths = new HashSet<String>();
        auths.add("Administrators");
        auths.add("Users");
        auths.add("SuperUsers");
        return auths;
    }

    /** {@inheritDoc} */
    public String getCurrentUserName() {
		return "";
	}

}
