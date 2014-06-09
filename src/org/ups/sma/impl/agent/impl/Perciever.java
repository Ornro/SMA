package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environement.EnvironmentManager;

/**
 * Created by Ben on 24/05/14.
 */
public class Perciever {
    private Filter perceptionFilter;

    public Perciever(Filter perceptionFilter) {
        this.perceptionFilter = perceptionFilter;
    }

    public Env getInformation(Agent agent){
        return EnvironmentManager.getInstance().getFullEnvironment().applyFilter(perceptionFilter, agent);
    }

}
