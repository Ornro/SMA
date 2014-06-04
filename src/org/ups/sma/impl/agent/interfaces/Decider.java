package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public interface Decider {
    public Map<Action,InteractiveEnvironmentObject> getNextMove(Agent agent);
}
