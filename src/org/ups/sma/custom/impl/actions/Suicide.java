package org.ups.sma.custom.impl.actions;

import org.ups.sma.domain.Action;
import org.ups.sma.domain.environment.InteractiveEnvironmentObject;
import org.ups.sma.impl.agent.Agent;

/**
 * Created by Ben on 08/06/14.
 */
public class Suicide extends Action {
    @Override
    public void execute(Agent a, InteractiveEnvironmentObject o) {
        a.destroy();
    }
}
