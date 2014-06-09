package org.ups.sma.domain.environment;

import org.ups.sma.custom.domain.environment.Location;
import org.ups.sma.impl.agent.Agent;

/**
 * To get a part of an environment,
 * an agent can call the method defined in the IEnvironmentManager
 * interface. This method uses the command pattern trough this interface.
 * This interface has only a method that must be defined in the filter.
 * The environment manager will use this interface method to generate
 * parts of the environment that are requested.
 */
public abstract class Filter {

    public abstract boolean isAcceptable(Location location , Agent agent);
}
