package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.Take;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;

/**
 * Box object.
 */
public class Box extends InteractiveEnvironmentObject {
    public Box(Location location) {
        super(new ArrayList<String>());
        this.location = location;
        this.availableActions.add("Take");
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
