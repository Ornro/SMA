package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Business on 04/06/14.
 */
public class Stock extends InteractiveEnvironmentObject {
    public Stock(Location location) {
        super(new ArrayList<String>(), location);
        this.availableActions.add("Dump");
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
