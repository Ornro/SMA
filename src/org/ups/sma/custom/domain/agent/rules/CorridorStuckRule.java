package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class CorridorStuckRule extends Rule {

    // if in corridor and stuck don't do anything
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.isInCorridor(a) && DecisionUtils.isRobotInFront(a) && !DecisionUtils.canMoveBack(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        return new ArrayList<Choice>();
    }
}
