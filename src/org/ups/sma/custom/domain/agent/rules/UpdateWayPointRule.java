package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 09/06/14.
 */
public class UpdateWayPointRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return true;
    }

    @Override
    public List<Choice> choices(Agent a) {
        DecisionUtils.setWayPoint(a);
        return new ArrayList<Choice>();
    }
}
