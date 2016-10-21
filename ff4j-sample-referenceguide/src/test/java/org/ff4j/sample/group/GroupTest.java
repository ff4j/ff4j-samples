package org.ff4j.sample.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.junit.Test;

public class GroupTest {

    @Test
    public void myGroupTest() {

        FF4j ff4j = new FF4j("ff4j-groups.xml");

        // Check features loaded
        assertEquals(4, ff4j.getFeatures().size());

        assertTrue(ff4j.exist("users-story1"));
        assertTrue(ff4j.getFeatureStore().existGroup("release-2.3"));
        System.out.println("Features loaded OK");

        // Given
        assertFalse(ff4j.check("users-story1"));
        assertFalse(ff4j.check("users-story2"));

        // When
        ff4j.enableGroup("release-2.3");

        // Then
        assertTrue(ff4j.check("users-story1"));
        assertTrue(ff4j.check("users-story2"));

    }


    @Test
    public void workWithGroupTest() {

        // Given
        FF4j ff4j = new FF4j("ff4j-groups.xml");
        assertTrue(ff4j.exist("featA"));

        // When
        ff4j.getFeatureStore().addToGroup("featA", "new-group");

        // Then
        assertTrue(ff4j.getFeatureStore().existGroup("new-group"));
        assertTrue(ff4j.getFeatureStore().readAllGroups().contains("new-group"));

        Map<String, Feature> myGroup = ff4j.getFeatureStore().readGroup("new-group");
        assertTrue(myGroup.containsKey("featA"));

        // A feature can be in a single group
        // Here changing => deleting the last element of a group => deleting the group
        ff4j.getFeatureStore().addToGroup("featA", "group2");

        assertFalse(ff4j.getFeatureStore().existGroup("new-group"));
    }
}
