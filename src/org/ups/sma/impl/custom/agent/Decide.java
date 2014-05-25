package org.ups.sma.impl.custom.agent;

import org.ups.sma.domain.Action;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Decider;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Decide implements Decider {
    @Override
    public List<Action> getNextMove(Agent agent) {
        return null;
    }
}
