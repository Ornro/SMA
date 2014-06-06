package org.ups.sma.custom.domain.action;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

public class WalkOn extends Action {

    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
        a.getState().position = ieo.getLocation();
    }
}
