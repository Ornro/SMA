package org.ups.sma.custom.impl.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environnement.Filter;
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
        Location currentLocation = agent.getLocation();
        for(int i=0; i<3; i++) {
            Location loc = new Location(currentLocation.x, currentLocation.y + i);

            if(loc.equals(currentLocation))
                return true;
        }

    }
}
