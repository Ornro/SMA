package org.ups.sma.custom.impl.actions;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environement.EnvironmentManager;

public class Dump extends Action {
    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        emanager.moveObject(a.getState().boxHolded, ieo.getLocation());
        a.getState().boxHolded = null;
        ieo.getAvailableActions().remove(this.getClass().getSimpleName());
    }
}
