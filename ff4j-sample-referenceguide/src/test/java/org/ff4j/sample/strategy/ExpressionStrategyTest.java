package org.ff4j.sample.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.ff4j.FF4j;
import org.junit.Test;

public class ExpressionStrategyTest {

    // ff4j
    private final FF4j ff4j = new FF4j("ff4j-strategy-expression.xml");

    @Test
    public void testExpressions() {

        // Given
        assertTrue(ff4j.exist("A"));
        assertTrue(ff4j.exist("B"));
        assertTrue(ff4j.exist("C"));
        ff4j.enable("D");
        ff4j.enable("E");
        ff4j.enable("F");

        // When A=FALSE, B=TRUE, C=TRUE
        assertFalse(ff4j.check("A"));
        assertTrue(ff4j.check("B"));
        assertTrue(ff4j.check("C"));

        // THEN
        // E = A AND B = FALSE AND TRUE = FALSE
        assertFalse(ff4j.check("E"));
        // F = A OR R = FALSE OR TRUE = TRUE
        assertTrue(ff4j.check("F"));
        // D = (A AND B) OR NOT(B) OR NOT(C) = (false & true) or false or false
        assertFalse(ff4j.check("D"));

        // When enabling A
        ff4j.enable("A");

        // THEN
        // E = A AND B = TRUE AND TRUE = TRUE
        assertTrue(ff4j.check("E"));
        // F = A AND B = TRUE OR TRUE = TRUE
        assertTrue(ff4j.check("F"));
        // D = (A AND B) OR NOT(B) OR NOT(C) = (true & true) or false or false
        assertTrue(ff4j.check("D"));
    }
}
