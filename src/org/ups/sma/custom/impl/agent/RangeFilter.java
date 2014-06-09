package org.ups.sma.custom.impl.agent;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Business on 09/06/14.
 */
public class RangeFilter extends Filter {

    public RangeFilter() { super(); }

    @Override
    public boolean isAcceptable(Location location, Agent agent) {
        Location aloc = agent.getLocation();
        return (location.x == aloc.x+1 || location.x == aloc.x-1 || location.y == aloc.y-1 || location.y == aloc.y+1);

    }
}
