package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Ben on 24/05/14.
 */
public interface Perciever {
    public Env getInformation(Agent agent);
}
