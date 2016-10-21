package org.ff4j.sample.strategy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.strategy.time.ReleaseDateFlipStrategy;
import org.junit.Test;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 Ff4J
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

/**
 * Testing class for {@link ReleaseDateFlipStrategy} class.
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class ReleaseDateFlipStrategyTest  {

    // initialize ff4j
    FF4j ff4j = new FF4j("ff4j-strategy-releasedate.xml");

    @Test
    public void testReleaseDateStrategy() throws ParseException {

        // Given
        assertTrue(ff4j.exist("PAST"));
        Feature fPast = ff4j.getFeature("PAST");
        ReleaseDateFlipStrategy rdsPast = (ReleaseDateFlipStrategy) fPast.getFlippingStrategy();
        assertTrue(rdsPast.evaluate("PAST", ff4j.getFeatureStore(), null));
        // Then
        assertTrue(ff4j.check("PAST"));

        // Given
        assertTrue(ff4j.exist("FUTURE"));
        Feature fFuture = ff4j.getFeature("FUTURE");
        ReleaseDateFlipStrategy rdsFuture = (ReleaseDateFlipStrategy) fFuture.getFlippingStrategy();
        assertFalse(rdsFuture.evaluate("FUTURE", ff4j.getFeatureStore(), null));
        

        // Then
        assertFalse(ff4j.check("FUTURE"));

    }

}
