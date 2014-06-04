package org.ups.sma.impl.agent.interfaces;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 24/05/14.
 */
public interface Effector {
    public void execute(Map<Action,InteractiveEnvironmentObject> toDo, Agent agent);
}
