package org.ups.sma.domain.environnement;

import org.ups.sma.domain.custom.environnement.Location;

/**
 * To get a part of an environment,
 * an agent can call the method defined in the IEnvironmentManager
 * interface. This method uses the command pattern trough this interface.
 * This interface has only a method that must be defined in the filter.
 * The environment manager will use this interface method to generate
 * parts of the environment that are requested.
 */
public interface Filter {
    public boolean isAcceptable(Location location,InteractiveEnvironmentObject object);
}
