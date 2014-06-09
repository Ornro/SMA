package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Created by Business on 04/06/14.
 */
public class Stock extends InteractiveEnvironmentObject {
    public Stock(Location location, List<String> availableActions) {
        super(availableActions);
        this.location = location;
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
