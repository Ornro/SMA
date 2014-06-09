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
        Location currentLocation = agent.getLocation();
        for(int i=0; i<3; i++) {
            Location locPlusY = new Location(currentLocation.x, currentLocation.y + i);
            Location locMinusY = new Location(currentLocation.x, currentLocation.y - i);
            Location locPlusX = new Location(currentLocation.x + 1, currentLocation.y);
            Location locMinusX = new Location(currentLocation.x - 1, currentLocation.y + i);
            Location locPlusXY = new Location(currentLocation.x + 1, currentLocation.y + i);
            Location locPlusXMinusY = new Location(currentLocation.x + 1, currentLocation.y - i);
            Location locMinusXPlusY = new Location(currentLocation.x - 1, currentLocation.y + i);
            Location locMinusXY = new Location(currentLocation.x - 1, currentLocation.y - i);

            if(locPlusY.equals(currentLocation) || locMinusY.equals(currentLocation)
                    || locPlusX.equals(currentLocation) || locMinusX.equals(currentLocation)
                    || locPlusXY.equals(currentLocation) || locPlusXMinusY.equals(currentLocation)
                    || locMinusXPlusY.equals(currentLocation) || locMinusXY.equals(currentLocation)) {
                return true;
            }
        }

        return false;
    }
}
