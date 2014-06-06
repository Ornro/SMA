package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.impl.actions.WalkOn;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Default object
 */
public class Default extends InteractiveEnvironmentObject {
    public Default(Location location, List<String> availableActions) {
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
