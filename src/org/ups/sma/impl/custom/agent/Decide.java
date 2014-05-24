package org.ups.sma.impl.custom.agent;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.custom.agent.State;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.interfaces.Decider;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Decide implements Decider {
    @Override
    public List<Action> getNextMove(Env environment, State state) {
        return null;
    }
}
