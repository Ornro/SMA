package org.ups.sma.impl.agent.impl;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.agent.interfaces.Effector;

import java.util.Map;
import java.util.Set;

/**
 * Created by Ben on 24/05/14.
 */
public class Act extends Effector {
    @Override
    public void execute(Map<Action,InteractiveEnvironmentObject> toDo, Agent agent) {
        Set<Map.Entry<Action,InteractiveEnvironmentObject>> entries = toDo.entrySet();
        for (Map.Entry entry : entries){
            Action a = (Action) entry.getKey();
            InteractiveEnvironmentObject object = (InteractiveEnvironmentObject) entry.getValue();
            a.execute(agent,object);
        }
    }
}
