package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.impl.Act;

/**
 * Created by Ben on 24/05/14.
 */
public abstract class Perciever {
    public abstract Env getInformation(Agent agent);

}
