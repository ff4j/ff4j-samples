package org.ff4j.hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.ff4j.FF4j;
import org.ff4j.exception.FeatureNotFoundException;
import org.junit.Test;

public class HelloTest {
    
    @Test
    public void my_first_test() {
        // Given, file is existing
        assertNotNull(getClass().getClassLoader().getResourceAsStream("ff4j.xml"));
        
        // When
        FF4j ff4j = new FF4j("ff4j.xml");

        // Then
        assertEquals(5, ff4j.getFeatures().size());
        assertTrue(ff4j.exist("hello"));
        assertTrue(ff4j.check("hello"));
        
        // Usage
        if (ff4j.check("hello")) {
            // hello is toggle on
            System.out.println("Hello World !!");
        }
        
        // Toggle (should not be performed programmatically except for tests I assume)
        ff4j.disable("hello");
        
        // Then
        assertFalse(ff4j.check("hello"));
        
    }
    
    @Test
    public void how_does_AutoCreate_Works() {
        // Given
        FF4j ff4j = new FF4j("ff4j.xml");
        assertFalse(ff4j.exist("does_not_exist"));
        
        // When
        try {
           if (ff4j.check("does_not_exist")) fail();
        } catch (FeatureNotFoundException fnfe) {
            // Then
            System.out.println("By default, feature not found throw exception");
        }
        
        // When
        ff4j.setAutocreate(true);
        // Then
        if (!ff4j.check("does_not_exist")) {
            System.out.println("Auto created and set as false");
        }
    }
    
    @Test
    public void how_groups_works() {
        // Given
        FF4j ff4j = new FF4j("ff4j.xml");
        assertTrue(ff4j.exist("userStory3_1"));
        assertTrue(ff4j.exist("userStory3_2"));
        assertTrue(ff4j.getFeatureStore().readAllGroups().contains("sprint_3"));
        assertEquals("sprint_3", ff4j.getFeature("userStory3_1").getGroup());
        assertEquals("sprint_3", ff4j.getFeature("userStory3_2").getGroup());
        assertFalse(ff4j.check("userStory3_1"));
        assertFalse(ff4j.check("userStory3_2"));
        
        // When, toggle group (fine grained are within "Store"
        ff4j.getFeatureStore().enableGroup("sprint_3");
        
        // Then
        assertTrue(ff4j.check("userStory3_1"));
        assertTrue(ff4j.check("userStory3_2"));
    }
}