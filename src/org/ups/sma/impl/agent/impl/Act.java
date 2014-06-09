package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.Choice;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Effector;

import java.util.List;

/**
 * Created by Ben on 24/05/14.
 */
public class Act extends Effector {
    @Override
    public void execute(List<Choice> choices, Agent agent) {
        for (Choice c : choices){
            c.getAction().execute(agent,c.getObject());
        }
    }
}
