package org.ff4j.sample.strategy;

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

import java.util.Calendar;
import java.util.Map;

import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.AbstractFlipStrategy;

/**
 * Sample strategy.
 */
public class OfficeHoursFlippingStrategy extends AbstractFlipStrategy {

    /** Start Hour. */
    private int start = 0;

    /** Hend Hour. */
    private int end = 0;

    /** {@inheritDoc} */
    @Override
    public void init(String featureName, Map<String, String> initValue) {
        super.init(featureName, initValue);
        assertRequiredParameter("startDate");
        assertRequiredParameter("endDate");
        start = new Integer(initValue.get("startDate"));
        end = new Integer(initValue.get("endDate"));
    }

    /** {@inheritDoc} */
    @Override
    public boolean evaluate(String fName, FeatureStore fStore, FlippingExecutionContext context) {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return (currentHour >= start && currentHour < end);
    }

}
