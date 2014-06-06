package org.ups.sma.domain.environnement;

import org.ups.sma.custom.domain.environnement.Location;
import org.ups.sma.impl.agent.Agent;

import java.util.Stack;

/**
 * To get a part of an environment,
 * an agent can call the method defined in the IEnvironmentManager
 * interface. This method uses the command pattern trough this interface.
 * This interface has only a method that must be defined in the filter.
 * The environment manager will use this interface method to generate
 * parts of the environment that are requested.
 */
public abstract class Filter {
    protected Agent agent;

    protected Filter(Agent agent){
        this.agent = agent;
    }

    public abstract boolean isAcceptable(Location location);
}