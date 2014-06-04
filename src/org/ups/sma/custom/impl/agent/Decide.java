package org.ups.sma.custom.impl.agent;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Decider;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public class Decide implements Decider {
    @Override
    public Map<Action,InteractiveEnvironmentObject> getNextMove(Agent agent) {
        return null;
    }
}
