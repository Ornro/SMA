package org.ups.sma.custom.impl.agent;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.Filter;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.impl.environement.EnvironmentManager;

/**
 * Created by Ben on 24/05/14.
 */
public class Perceive extends Perciever {

    private Filter perceptionFilter;

    public Perceive(Filter perceptionFilter) {
        this.perceptionFilter = perceptionFilter;
    }

    @Override
    public Env getInformation(Agent agent) {
        return EnvironmentManager.getInstance().getFullEnvironment().applyFilter(perceptionFilter,agent);
    }
}
