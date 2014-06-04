package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.Dump;
import org.ups.sma.domain.custom.environnement.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

/**
 * Created by Business on 04/06/14.
 */
public class Depot extends InteractiveEnvironmentObject {
    public Depot(Location location) {
        super();
        this.location = location;
        this.availableActions.add(new Dump());
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}
