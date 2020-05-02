package org.ff4j.sample;

/*
 * #%L
 * ff4j-sample-web
 * %%
 * Copyright (C) 2013 - 2017 FF4J
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

import java.io.Serializable;

import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.AbstractFlipStrategy;

public class AmichaiuStrategy  extends AbstractFlipStrategy implements Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -5355317849872321316L;

    /** {@inheritDoc} */
    @Override
    public boolean evaluate(String featureName, FeatureStore store, FlippingExecutionContext executionContext) {
        return false;
    }

}
