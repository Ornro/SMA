package org.ups.sma.custom.domain.environnement.objects;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Created by Business on 06/06/14.
 */
public class Take extends InteractiveEnvironmentObject {
    public Take(Location location, List<String> availableActions) {
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