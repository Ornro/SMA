package org.ups.sma.custom.domain.action;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.custom.environnement.Location;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.domain.environnement.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.impl.environement.EnvironmentManager;

public class Take extends Action {

    @Override
    public void execute(Agent a, InteractiveEnvironmentObject ieo){
        EnvironmentManager emanager = EnvironmentManager.getInstance();
        Env env = emanager.getFullEnvironment();
        //TODO remove box from its position
        a.getState().holding = true;
    }
}
