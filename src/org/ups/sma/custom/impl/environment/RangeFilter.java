package org.ups.sma.custom.impl.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Business on 09/06/14.
 */
public class RangeFilter extends Filter {

    public RangeFilter() { super(); }

    @Override
    public boolean isAcceptable(Location location, Agent agent) {
        return false;
    }
}
