package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class CorridorEncounterRule extends Rule {

    // move back if in corridor and robot in front
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.isInCorridor(a) && DecisionUtils.isRobotInFront(a) && DecisionUtils.canMoveBack(a);
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("encount corr Rule");
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.moveBack(a));
        return choices;
    }
}
