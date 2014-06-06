package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.action.Dump;
import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.ArrayList;

/**
 * Created by Business on 04/06/14.
 */
public class Depot extends InteractiveEnvironmentObject {
    public Depot(Location location) {
        super(new ArrayList<String>());
        this.location = location;
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
