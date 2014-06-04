package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.Take;
import org.ups.sma.domain.custom.environnement.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

/**
 * Box object.
 */
public class Box extends InteractiveEnvironmentObject {
    public Box(Location location) {
        super();
        this.location = location;
        this.availableActions.add(new Take());
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
