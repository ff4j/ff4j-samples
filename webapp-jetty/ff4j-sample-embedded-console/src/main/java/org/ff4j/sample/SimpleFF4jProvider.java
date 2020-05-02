package org.ff4j.sample;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jProvider;

public class SimpleFF4jProvider implements FF4jProvider {

    /** ff4j instance. */
    private final FF4j ff4j;

    /**
     * Default constructeur invoked by servlet.
     */
    public SimpleFF4jProvider() {
        ff4j = new FF4j("ff4j.xml");
        ff4j.setAuthorizationsManager(new MockAuthorisationManager());
    }

    /**
     * Getter accessor for attribute 'fF4j'.
     *
     * @return current value of 'fF4j'
     */
    @Override
    public FF4j getFF4j() {
        return ff4j;
    }

}
