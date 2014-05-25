package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.Action;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Effector;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Act implements Effector {
    @Override
    public void execute(List<Action> toDo, Agent agent) {
        for (Action a : toDo){
            a.execute(agent);
        }
    }

}
