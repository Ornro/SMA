package org.ups.sma.custom.impl.actions;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environement.EnvironmentManager;

import java.util.Stack;

public class WalkOn extends Action {

    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
       EnvironmentManager emanager = EnvironmentManager.getInstance();
       emanager.moveObject(a,ieo.getLocation());
    }
}
