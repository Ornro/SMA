package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Box object.
 */
public class Box extends InteractiveEnvironmentObject {
    public Box(Location location, List<String> availableActions) {
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
