package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environnement.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class CorridorFreeRule extends Rule {

    // if in corridor and can advance go
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.isInCorridor(a) && !DecisionUtils.isRobotInFront(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.moveForward(a));
        return choices;
    }
}
