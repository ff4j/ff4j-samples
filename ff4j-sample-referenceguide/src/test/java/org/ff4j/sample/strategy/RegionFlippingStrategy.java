package org.ff4j.sample.strategy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.ff4j.core.FeatureStore;
import org.ff4j.core.FlippingExecutionContext;
import org.ff4j.strategy.AbstractFlipStrategy;

/**
 * Filter features for a subset of regions.
 * 
 * @author <a href="mailto:cedrick.lunven@gmail.com">Cedrick LUNVEN</a>
 */
public class RegionFlippingStrategy extends AbstractFlipStrategy {

    /** initial parameter. */
    private static final String INIT_PARAMNAME_REGIONS = "grantedRegions";

    /** current user attribute */
    public static final String PARAMNAME_USER_REGION = "region";

    /** Initial Granted Regions. */
    private final Set<String> setOfGrantedRegions = new HashSet<String>();

    /** {@inheritDoc} */
    @Override
    public void init(String featureName, Map<String, String> initValue) {
        super.init(featureName, initValue);
        assertRequiredParameter(INIT_PARAMNAME_REGIONS);
        String[] arrayOfRegions = initValue.get(INIT_PARAMNAME_REGIONS).split(",");
        setOfGrantedRegions.addAll(Arrays.asList(arrayOfRegions));
    }

    /** {@inheritDoc} */
    @Override
    public boolean evaluate(String fName, FeatureStore fStore, FlippingExecutionContext ctx) {
        // true means required here
        String userRegion = ctx.getString(PARAMNAME_USER_REGION, true);
        return setOfGrantedRegions.contains(userRegion);
    }

}
