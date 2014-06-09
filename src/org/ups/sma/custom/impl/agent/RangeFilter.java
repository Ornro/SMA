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
        int maxX=aloc.x+1;
        int maxY=aloc.y+1;
        int minY=aloc.y-1;
        int minX=aloc.x-1;

        return location.x<=maxX && location.x>=minX && location.y>=minY && location.y<=maxY;
    }
}
