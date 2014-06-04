package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.WalkOn;
import org.ups.sma.domain.custom.environnement.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

/**
 * Default object
 */
public class Default extends InteractiveEnvironmentObject {
    public Default(Location location) {
        super();
        this.location = location;
        this.availableActions.add(new WalkOn());
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
