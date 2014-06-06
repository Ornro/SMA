package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.Take;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;

/**
 * Created by Business on 04/06/14.
 */
public class Stock extends InteractiveEnvironmentObject {
    public Stock(Location location) {
        super(new ArrayList<Action>());
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
