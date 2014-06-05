package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.WalkOn;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;

/**
 * Default object
 */
public class Default extends InteractiveEnvironmentObject {
    public Default(Location location) {
        super(new ArrayList<Action>());
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
