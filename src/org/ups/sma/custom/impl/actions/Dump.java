package org.ups.sma.custom.impl.actions;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environment.EnvironmentManager;

public class Dump extends Action {
    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        //emanager.moveObject(a.getState().boxHeld, ieo.getLocation());
        a.getState().boxHeld = null;
        ieo.getAvailableActions().remove(this.getClass().getSimpleName());
        ieo.getAvailableActions().add("WalkOn");
    }
}
