package org.ups.sma.custom.domain.agent.rules;

import org.ups.sma.domain.Choice;
import org.ups.sma.domain.environment.Rule;
import org.ups.sma.impl.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 07/06/14.
 */
public class MoveForwardRule extends Rule {
    @Override
    public boolean condition(Agent a) {
        return DecisionUtils.canMoveForward(a) && !DecisionUtils.isInCorridor(a) && !(DecisionUtils.canDump(a) && DecisionUtils.isBoxHeld(a)) && !(DecisionUtils.canGet(a) && !DecisionUtils.isBoxHeld(a));
    }

    @Override
    public List<Choice> choices(Agent a) {
        System.out.println("move Rule");
        List<Choice> choices = new ArrayList<Choice>();
        choices.add(DecisionUtils.moveForward(a));
        return choices;
    }
}
