package org.ups.sma.impl.custom.agent;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Perciever;
import org.ups.sma.impl.environement.EnvironmentManager;

/**
 * Created by Ben on 24/05/14.
 */
public class Percieve implements Perciever {

    @Override
    public Env getInformations(Agent agent) {
        return EnvironmentManager.getInstance().getEnvironnement(agent);
    }
}
