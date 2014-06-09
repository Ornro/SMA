package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.environment.Env;
import org.ups.sma.domain.environment.Filter;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environment.EnvironmentManager;

/**
 * Created by Ben on 24/05/14.
 */
public class Perceiver {
    private Filter perceptionFilter;

    public Perceiver(Filter perceptionFilter) {
        this.perceptionFilter = perceptionFilter;
    }

    public Env getInformation(Agent agent){
        return EnvironmentManager.getInstance().getFullEnvironment().applyFilter(perceptionFilter, agent);
    }

}
