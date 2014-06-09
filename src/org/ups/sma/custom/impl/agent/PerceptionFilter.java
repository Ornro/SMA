package org.ups.sma.custom.impl.agent;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Business on 09/06/14.
 */
public class PerceptionFilter extends Filter {

    public PerceptionFilter() {
        super();
    }

    @Override
    public boolean isAcceptable(Location location, Agent agent) {
        Location aloc = agent.getLocation();
        int maxX=aloc.x+3;
        int maxY=aloc.y+3;
        int minY=aloc.y-3;
        int minX=aloc.x-3;

        return location.x<=maxX && location.x>=minX && location.y>=minY && location.y<=maxY;
    }
}
