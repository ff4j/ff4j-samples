package org.ff4j.sample.strategy;

/*
 * #%L
 * ff4j-core
 * $Id:$
 * $HeadURL:$
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingStrategy;
import org.junit.Test;

public class OfficeHoursFlippingStrategyTest  {
	
    // ff4j
    private final FF4j ff4j = new FF4j("ff4j-strategy-1.xml");

	@Test
    public void testCustomStrategy() throws Exception {
        assertTrue(ff4j.exist("sayHello"));
        FlippingStrategy fs = ff4j.getFeature("sayHello").getFlippingStrategy();
        assertTrue(fs.getClass() == OfficeHoursFlippingStrategy.class);
        assertEquals("9", fs.getInitParams().get("startDate"));

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        boolean isNowOfficeTime = (hour > 9) & (hour < 18);
        assertEquals(isNowOfficeTime, ff4j.check("sayHello"));
	}
		
}
