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
        Location currentLocation = agent.getLocation();
        Location locPlusY = new Location(currentLocation.x, currentLocation.y + 1);
        Location locMinusY = new Location(currentLocation.x, currentLocation.y - 1);
        Location locPlusX = new Location(currentLocation.x + 1, currentLocation.y);
        Location locMinusX = new Location(currentLocation.x - 1, currentLocation.y + 1);
        Location locPlusXY = new Location(currentLocation.x + 1, currentLocation.y + 1);
        Location locPlusXMinusY = new Location(currentLocation.x + 1, currentLocation.y - 1);
        Location locMinusXPlusY = new Location(currentLocation.x - 1, currentLocation.y + 1);
        Location locMinusXY = new Location(currentLocation.x - 1, currentLocation.y - 1);

        if(locPlusY.equals(currentLocation) || locMinusY.equals(currentLocation)
                || locPlusX.equals(currentLocation) || locMinusX.equals(currentLocation)
                || locPlusXY.equals(currentLocation) || locPlusXMinusY.equals(currentLocation)
                || locMinusXPlusY.equals(currentLocation) || locMinusXY.equals(currentLocation)) {
            return true;
        } else {
            return false;
        }
    }
}
