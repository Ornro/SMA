package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Default object
 */
public class Default extends InteractiveEnvironmentObject {


    public Default(Location location) {
        super(new ArrayList<String>(), location);
        this.availableActions.add("WalkOn");
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
