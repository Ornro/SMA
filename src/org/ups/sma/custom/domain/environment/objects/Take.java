package org.ups.sma.custom.domain.environment.objects;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;

import java.util.List;

/**
 * Created by Business on 06/06/14.
 */
public class Take extends InteractiveEnvironmentObject {
    public Take(Location location, List<String> availableActions) {
        super(availableActions, location);
    }

    @Override
    public void destroy() {

    }

    @Override
    public InteractiveEnvironmentObject clone() {
        return null;
    }
}