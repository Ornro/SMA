package org.ups.sma.impl.environement;

import org.ups.sma.domain.custom.agent.Coord;
import org.ups.sma.domain.environnement.Env;
import org.ups.sma.impl.agent.Agent;
import org.ups.sma.interfaces.IEnvironnementManager;
import org.ups.sma.interfaces.Statefull;

/**
 * Created by Ben on 24/05/14.
 */
public class EnvironmentManager implements IEnvironnementManager {

    private static EnvironmentManager instance;

    public static EnvironmentManager getInstance(){
        return instance;
    }

    @Override
    public Env getEnvironnement(Agent agent) {
        return null;
    }

    @Override
    public Env getFullEnvironnement() {
        return null;
    }

    @Override
    public void notifyStateChange(Statefull s) {

    }
}
