package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.custom.agent.State;
import org.ups.sma.domain.environnement.Env;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public interface Decider {
    public List<Action> getNextMove(Env environment, State state);
}
