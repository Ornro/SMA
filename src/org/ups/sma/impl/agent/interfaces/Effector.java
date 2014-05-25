package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.Action;
import org.ups.sma.impl.agent.Agent;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public interface Effector {
    public void execute(List<Action> toDo, Agent agent);
}
